package ng.com.justjava.bookkeeping.Exception;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;


@Component
public class MyCustomErrorAttributes extends DefaultErrorAttributes {
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);
        errorAttributes.put("error", "false");
        if(errorAttributes.get("status").toString().equalsIgnoreCase("500"))
            errorAttributes.put("error", "true");


        errorAttributes.put("locale", webRequest.getLocale()
                .toString());
        return errorAttributes;
    }
}
