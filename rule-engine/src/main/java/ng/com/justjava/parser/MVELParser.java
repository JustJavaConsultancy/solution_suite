package ng.com.justjava.parser;

import lombok.extern.slf4j.Slf4j;
import org.mvel2.MVEL;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class MVELParser {

    public Object parseMvelExpression( String expression, Map<String, Object> inputObjects){
        try {
            return MVEL.eval(expression,inputObjects);
        }catch (Exception e){
            log.error("Can not parse Mvel Expression : {} Error: {}", expression, e.getMessage());
        }
        return false;
    }
}
