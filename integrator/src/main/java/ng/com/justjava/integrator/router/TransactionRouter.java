package ng.com.justjava.integrator.router;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.http.base.HttpOperationFailedException;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class TransactionRouter extends RouteBuilder {

    private final Environment env;
    @Autowired
    private DiscoveryClient dClient;

    public TransactionRouter(Environment env) {
        this.env = env;
    }

    @Override
    public void configure() throws Exception {
        rest("/api/")
                .post("/createjournal")
                .to("direct:createjournal");

        rest("/api/")
                .post("/createjournal_manual")
                .to("direct:createjournal_manual");

        rest("/api/")
                .get("/journalbyreference/{reference}")
                .to("direct:journalbyreference");

        //http://localhost:8081/journals/search/byRef?transReference=352_1114808333541099

        from("direct:createjournal")
                .log("Body: ${body}")
                .marshal().json(JsonLibrary.Jackson)
                .to(String.format("%s/accounting?bridgeEndpoint=true", dClient.getInstances("bookkeeping").get(0).getUri()))
                .unmarshal().json()
                .log("Body: ${body}");


        from("direct:createjournal_manual")
                .log("Body: ${body}")
                .marshal().json(JsonLibrary.Jackson)
                .to(String.format("%s/manualaccounting?bridgeEndpoint=true&throwExceptionOnFailure=false", dClient.getInstances("bookkeeping").get(0).getUri()))
                .unmarshal().json()
                .log("Body: ${body}");

        //.log("Body: ${body}")
        from("direct:journalbyreference")
                .toD(String.format("%s/journals/search/byRef?transReference=${header.reference}&bridgeEndpoint=true", dClient.getInstances("bookkeeping").get(0).getUri()))
                .unmarshal().json()
                .log("Body: ${body}");
    }
}
