package ng.com.justjava.bookkeeping.rest;

import ng.com.justjava.bookkeeping.db.view.TrialBalanceView;
import ng.com.justjava.bookkeeping.services.ReportInterface;
import ng.com.justjava.ruleEngine.rulesImpl.accountingRule.AccountingDetails;
import ng.com.justjava.ruleEngine.rulesImpl.accountingRule.TransactionDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReportREST {
    @Autowired
    private ReportInterface reportInterface;

    @GetMapping(value = "/trialbalance")
    public ResponseEntity<?> postTransactionDetails() {
        System.out.println(" ENtering here.............");
        List<TrialBalanceView> trialBalanceView = reportInterface.getTrialBalance();
        return ResponseEntity.ok(trialBalanceView);
    }
}
