package ng.com.justjava.bookkeeping.rest.feignclient;

import ng.com.justjava.ruleEngine.rulesImpl.accountingRule.AccountingDetails;
import ng.com.justjava.ruleEngine.rulesImpl.accountingRule.TransactionDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("rule-engine")
public interface RuleClient {
    @PostMapping("/accounting")
    public AccountingDetails details(TransactionDetails transactionDetails);
}
