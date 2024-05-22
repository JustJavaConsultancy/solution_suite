package ng.com.justjava.ruleEngine.rulesImpl.accountingRule;

import lombok.extern.slf4j.Slf4j;
import ng.com.justjava.domain.RuleNamespace;
import ng.com.justjava.ruleEngine.InferenceEngine;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AccountingInferenceEngine extends InferenceEngine<TransactionDetails, AccountingDetails> {
    @Override
    protected AccountingDetails initializeOutputResult() {
        return AccountingDetails.builder().build();
    }

    @Override
    protected RuleNamespace getRuleNamespace() {
        return RuleNamespace.ACCOUNTING;
    }
}
