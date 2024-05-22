package ng.com.justjava.bookkeeping.rest;

import ng.com.justjava.bookkeeping.services.AccountPosting;
import ng.com.justjava.ruleEngine.rulesImpl.accountingRule.AccountingDetails;
import ng.com.justjava.ruleEngine.rulesImpl.accountingRule.TransactionDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountingREST {
    @Autowired
    AccountPosting accountPosting;
    @PostMapping(value = "/accounting")
    public ResponseEntity<?> postTransactionDetails(@RequestBody TransactionDetails transactionDetails) {
        System.out.println(" ENtering here.............");
        AccountingDetails result = accountPosting.post(transactionDetails);
        return ResponseEntity.ok(result);
    }
    @PostMapping(value = "/manualaccounting")
    public ResponseEntity<?> postTransactionDetails(@RequestBody AccountingDetails accountingDetails) {
        System.out.println(" ENtering here.............");
        AccountingDetails result = accountPosting.post(accountingDetails);
        return ResponseEntity.ok(result);
    }
}
