package ng.com.justjava.rest;

import com.google.common.base.Enums;
import lombok.extern.slf4j.Slf4j;
import ng.com.justjava.domain.KnowledgeBaseService;
import ng.com.justjava.domain.Rule;
import ng.com.justjava.domain.RuleNamespace;
import ng.com.justjava.ruleEngine.RuleEngine;
import ng.com.justjava.ruleEngine.rulesImpl.accountingRule.AccountingDetails;
import ng.com.justjava.ruleEngine.rulesImpl.accountingRule.AccountingInferenceEngine;
import ng.com.justjava.ruleEngine.rulesImpl.accountingRule.TransactionDetails;
import ng.com.justjava.rulesImpl.loanRuleEngine.LoanDetails;
import ng.com.justjava.rulesImpl.loanRuleEngine.LoanInferenceEngine;
import ng.com.justjava.rulesImpl.loanRuleEngine.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class RuleEngineRestController {
    @Autowired
    private KnowledgeBaseService knowledgeBaseService;
    @Autowired
    private RuleEngine ruleEngine;
    @Autowired
    private AccountingInferenceEngine accountingInferenceEngine;

    @Autowired
    private LoanInferenceEngine loanInferenceEngine;
    //@Autowired
    @GetMapping(value = "/get-all-rules/{ruleNamespace}")
    public ResponseEntity<?> getRulesByNamespace(@PathVariable("ruleNamespace") String ruleNamespace) {
        RuleNamespace namespace = Enums.getIfPresent(RuleNamespace.class, ruleNamespace.toUpperCase()).or(RuleNamespace.DEFAULT);
        List<Rule> allRules = knowledgeBaseService.getAllRuleByNamespace(namespace.toString());
        return ResponseEntity.ok(allRules);
    }

    @GetMapping(value = "/get-all-rules")
    public ResponseEntity<?> getAllRules() {
        List<Rule> allRules = knowledgeBaseService.getAllRules();
        return ResponseEntity.ok(allRules);
    }

    @PostMapping(value = "/loan")
    public ResponseEntity<?> postUserLoanDetails(@RequestBody UserDetails userDetails) {
        LoanDetails result = (LoanDetails) ruleEngine.run(loanInferenceEngine, userDetails);
        return ResponseEntity.ok(result);
    }
    @PostMapping(value = "/accounting")
    public ResponseEntity<?> postTransactionDetails(@RequestBody TransactionDetails transactionDetails) {
        AccountingDetails result = (AccountingDetails) ruleEngine.run(accountingInferenceEngine, transactionDetails);
        return ResponseEntity.ok(result);
    }
}