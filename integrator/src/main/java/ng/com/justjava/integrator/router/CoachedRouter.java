package ng.com.justjava.integrator.router;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class CoachedRouter extends RouteBuilder {
    private final Environment env;

    public CoachedRouter(Environment env) {
        this.env = env;
    }

    @Override
    public void configure() throws Exception {
        restConfiguration()
                .component("servlet")
                .contextPath(env.getProperty("camel.component.servlet.mapping.contextPath", "/rest/*"))
                .port(env.getProperty("server.port", "8080"))
                .enableCORS(true);
                //.bindingMode(RestBindingMode.);

        rest("/api/")
                .get("/videocall")
                .produces("text/html")
                .to("direct:videocall");

        from("direct:videocall")
                .to("http://localhost:8383/login.html?bridgeEndpoint=true")
                //.unmarshal().json()
                .log("Body: ${body}");
    }
}
