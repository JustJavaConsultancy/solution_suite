package ng.com.justjava.integrator.router;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class OrganizationRouter extends RouteBuilder {
    private final Environment env;
    @Autowired
    private DiscoveryClient dClient;

    public OrganizationRouter(Environment env) {
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
                .get("/organizations")
                .to("direct:organizations");

        from("direct:organizations")
                .toD(String.format("%s/organizations?bridgeEndpoint=true", dClient.getInstances("bookkeeping").get(0).getUri()))
                .unmarshal().json()
                .log("Body: ${body}");
    }
}
