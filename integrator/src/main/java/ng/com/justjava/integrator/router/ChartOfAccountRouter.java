package ng.com.justjava.integrator.router;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ChartOfAccountRouter extends RouteBuilder {
    public static final String URL_RULES = "%s/get-all-rules?bridgeEndpoint=true";
    private final Environment env;
    @Autowired
    private DiscoveryClient dClient;

    public ChartOfAccountRouter(Environment env) {
        this.env = env;
    }

    @Override
    public void configure() throws Exception {
        restConfiguration()
                .component("servlet")
                .contextPath(env.getProperty("camel.component.servlet.mapping.contextPath", "/rest/*"))
                .port(env.getProperty("server.port", "8080"))
                .enableCORS(true)
                .bindingMode(RestBindingMode.json);

/*        from("kafka:mytopic")
                .log("Consuming from Kafka Topic==== ${body}");*/

        rest("/api/")
                .get("/glaccountsbyorg/{org}")
                .to("direct:organizationglaccounts");
        rest("/api/")
                .get("/glaccountbyid/{id}")
                .to("direct:glaccountbyid");

        rest("/api/")
                .post("/createglaccount")
                .to("direct:createglaccount");

        rest("/api/")
                .put("/editglaccount/{id}/")
                .to("direct:editglaccounting");

        rest("/api/")
                .get("/categories")
                .to("direct:categories");

        from("direct:organizationglaccounts")
                .toD(String.format("%s/gLAccounts/search/byorganization?code=${header.org}&bridgeEndpoint=true", dClient.getInstances("bookkeeping").get(0).getUri()))
                .unmarshal().json()
                //.to("kafka:mytopic")
                .log("Body: ${body}");

        from("direct:glaccountbyid")
                .toD(String.format("%s/gLAccounts/${header.id}?bridgeEndpoint=true&projection=GlAccountView", dClient.getInstances("bookkeeping").get(0).getUri()))
                .unmarshal().json()

                .log("Body: ${body}");

        from("direct:createglaccount")
                .log("Body: ${body}")
                .marshal().json(JsonLibrary.Jackson)
                .to(String.format("%s/gLAccounts?bridgeEndpoint=true", dClient.getInstances("bookkeeping").get(0).getUri()))
                .unmarshal().json()
                .log("Body: ${body}");

        //String url = "http://localhost:8081/gLAccounts/52?bridgeEndpoint=true";

        from("direct:editglaccounting")
                .log("Header ID Sent : ${header.id}")
                .marshal().json(JsonLibrary.Jackson)
                //.toD(url)
                .toD(String.format("%s/gLAccounts/${header.id}?bridgeEndpoint=true", dClient.getInstances("bookkeeping").get(0).getUri()))
                .unmarshal().json()
                .log("Body: ${body}");

        from("direct:categories")
                .toD(String.format("%s/gLHeaders?bridgeEndpoint=true", dClient.getInstances("bookkeeping").get(0).getUri()))
                .unmarshal().json()
                .log("Body: ${body}");
    }
}
