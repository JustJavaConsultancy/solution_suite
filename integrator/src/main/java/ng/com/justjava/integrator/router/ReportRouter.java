package ng.com.justjava.integrator.router;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ReportRouter extends RouteBuilder {
    public static final String URL_RULES = "%s/get-all-rules?bridgeEndpoint=true";
    private final Environment env;
    @Autowired
    private DiscoveryClient dClient;

    public ReportRouter(Environment env) {
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

        rest("/api/")
                .get("/statement/{code}/{startdate}/{enddate}")
                .to("direct:statement");


        rest("/api/")
                .get("/trialbalance/{startdate}/{enddate}")
                .to("direct:trialbalance");

        rest("/api/")
                .get("/journals")
                .to("direct:journals");

        rest("/api/")
                .get("/finacialreports")
                .to("direct:finacialreports");

        rest("/api/")
                .get("/finacialreports/{id}")
                .to("direct:particularfinacialreports");


        from("direct:statement")
                .toD(String.format("%s/journalLines/search/statment?code=${header.code}&dateTimeCreatedStart=${header.startdate}&dateTimeCreatedEnd=${header.enddate}&bridgeEndpoint=true", dClient.getInstances("bookkeeping").get(0).getUri()))
                .unmarshal().json()
                .log("Body: ${body}");

        from("direct:trialbalance")
                .toD(String.format("%s/trialbalance?startDate=${header.startdate}&endDate=${header.enddate}&bridgeEndpoint=true", dClient.getInstances("bookkeeping").get(0).getUri()))
                .unmarshal().json()
                .log("Body: ${body}");

        from("direct:journals")
                .toD(String.format("%s/journals?projection=JournalView&bridgeEndpoint=true", dClient.getInstances("bookkeeping").get(0).getUri()))
                .unmarshal().json()
                .log("Body: ${body}");

        from("direct:finacialreports")
                .to(String.format("%s/financialReportSetups?bridgeEndpoint=true", dClient.getInstances("bookkeeping").get(0).getUri()))
                .unmarshal().json()
                .log("Body: ${body}");

        from("direct:particularfinacialreports")
                .toD(String.format("%s/financialReportSetups/${header.id}?bridgeEndpoint=true", dClient.getInstances("bookkeeping").get(0).getUri()))
                .unmarshal().json()
                .log("Body: ${body}");

    }
}
