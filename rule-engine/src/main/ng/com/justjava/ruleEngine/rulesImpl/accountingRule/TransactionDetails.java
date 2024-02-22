package ng.com.justjava.ruleEngine.rulesImpl.accountingRule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDetails {
    String transactionRef;
    LocalDate dueDate;
    Double transactionAmount;
    String narration;
}
