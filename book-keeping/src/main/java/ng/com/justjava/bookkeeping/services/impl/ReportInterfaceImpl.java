package ng.com.justjava.bookkeeping.services.impl;

import ng.com.justjava.bookkeeping.db.JournalLine;
import ng.com.justjava.bookkeeping.db.JournalLineRepository;
import ng.com.justjava.bookkeeping.db.view.TrialBalanceView;
import ng.com.justjava.bookkeeping.services.ReportInterface;
import ng.com.justjava.ruleEngine.rulesImpl.accountingRule.TransactionDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportInterfaceImpl implements ReportInterface {
    @Autowired
    private JournalLineRepository journalLineRepository;
    @Override
    public List<TrialBalanceView>  getTrialBalance() {
        System.out.println(" Right here 1");
        List<TrialBalanceView> views = journalLineRepository.trialBalance();
        System.out.println(" Right here 2");
        return views;
    }
}
