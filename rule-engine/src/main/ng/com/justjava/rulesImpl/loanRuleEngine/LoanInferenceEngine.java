package ng.com.justjava.rulesImpl.loanRuleEngine;

import lombok.extern.slf4j.Slf4j;
import ng.com.justjava.domain.RuleNamespace;
import ng.com.justjava.ruleEngine.InferenceEngine;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoanInferenceEngine extends InferenceEngine<UserDetails, LoanDetails> {

    @Override
    protected RuleNamespace getRuleNamespace() {
        return RuleNamespace.LOAN;
    }

    @Override
    protected LoanDetails initializeOutputResult() {
        return LoanDetails.builder().build();
    }
}