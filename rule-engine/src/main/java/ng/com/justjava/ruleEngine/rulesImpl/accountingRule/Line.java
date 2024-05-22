package ng.com.justjava.ruleEngine.rulesImpl.accountingRule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Line {
    Double credit;
    Double debit;
    String gl;
}
