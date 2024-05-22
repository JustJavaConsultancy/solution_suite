package ng.com.justjava.integrator.router;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SoapToJSONRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("servlet:/soap")
                .routeId("soap-to-json-route")
                .log("Received SOAP request: ${body}") // Log the incoming SOAP request
                .convertBodyTo(String.class) // Convert incoming SOAP request to String
                .log("Converted SOAP to String: ${body}") // Log the converted SOAP message
                .process(exchange -> {
                    // Extract relevant data from SOAP message and construct JSON
                    String soapBody = exchange.getIn().getBody(String.class);
                    String jsonResponse = convertSoapToJson(soapBody); // Method to convert SOAP to JSON
                    // Set JSON response as the new message body
                    exchange.getMessage().setBody(jsonResponse);
                })
                .log("Converted SOAP to JSON: ${body}") // Log the converted JSON response
                .end(); // End of the route
    }

    // Method to convert SOAP message to JSON
    private String convertSoapToJson(String soapBody) {
        // Your logic to parse the SOAP message and construct JSON goes here
        // For demonstration purposes, let's assume the SOAP message contains XML data
        // We'll use a library like Jackson to convert XML to JSON
        // You'll need to include the necessary dependencies and implement the conversion logic
        // Here's a simple example using Jackson ObjectMapper
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode xmlNode = objectMapper.readTree(soapBody);
            String jsonResponse = objectMapper.writeValueAsString(xmlNode);
            return jsonResponse;
        } catch (Exception e) {
            // Handle exceptions appropriately
            e.printStackTrace();
            return "{\"error\": \"Failed to convert SOAP to JSON\"}";
        }
    }
}
