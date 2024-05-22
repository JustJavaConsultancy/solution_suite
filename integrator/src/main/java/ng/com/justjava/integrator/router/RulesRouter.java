package ng.com.justjava.integrator.router;


import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


@Component
public class RulesRouter extends RouteBuilder {
    public static final String URL_DISCOVER_BALANCE = "%s/trialbalance?bridgeEndpoint=true";

    public static final String URL_RULES = "%s/get-all-rules?bridgeEndpoint=true";
    private final Environment env;
    @Autowired
    private DiscoveryClient dClient;

    public RulesRouter(Environment env) {
        this.env = env;
    }

    @Override
    public void configure() throws Exception {

        restConfiguration()
                .component("servlet")
                .contextPath(env.getProperty("camel.component.servlet.mapping.contextPath", "/rest/*"))
                .apiContextPath("/api-doc")
                .apiProperty("api.title", "Accounting Module of Financial")
                .apiProperty("api.version", "1.0")
                .apiProperty("cors", "true")
                .apiContextRouteId("doc-api")
                .port(env.getProperty("server.port", "8080"))
                .enableCORS(true)
                .bindingMode(RestBindingMode.json);




        rest("/api/")
                .get("/rules")
                .to("direct:rules");
        rest("/api/")
                .get("/rulesbyorg/{org}")
                .to("direct:rulesbyorg");

        rest("/api/")
                .post("/rule")
                .to("direct:create-rule");



        from("direct:rules")
                //.log("Body: ${body}")
                .to(String.format("%s/get-all-rules?bridgeEndpoint=true", dClient.getInstances("rule-engine").get(0).getUri()))
                .unmarshal().json()
                .log("Body: ${body}");

        from("direct:rulesbyorg")
                //.log("Body: ${body}")
                .toD(String.format("%s/get-all-org-rules/${header.org}?bridgeEndpoint=true", dClient.getInstances("rule-engine").get(0).getUri()))
                .unmarshal().json()
                .log("Body: ${body}");

        from("direct:create-rule")
                .log("Body: ${body}")
                .marshal().json(JsonLibrary.Jackson)
                .to(String.format("%s/rule?bridgeEndpoint=true", dClient.getInstances("rule-engine").get(0).getUri()))
                .unmarshal().json()
                .log("Body: ${body}");


    }
}
