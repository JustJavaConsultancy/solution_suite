package ng.com.justjava.bookkeeping.rest.feignclient;

import ng.com.justjava.bookkeeping.dto.AccountingDetails;
import ng.com.justjava.bookkeeping.dto.TransactionDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("rule-engine")
public interface RuleClient {
    @PostMapping("/accounting")
    public AccountingDetails details(TransactionDetails transactionDetails);
}
