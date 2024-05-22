package ng.com.justjava.bookkeeping.services.impl;
import ng.com.justjava.bookkeeping.db.JournalLineRepository;
import ng.com.justjava.bookkeeping.db.view.TrialBalanceView;
import ng.com.justjava.bookkeeping.services.ReportInterface;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportInterfaceImpl implements ReportInterface {
    @Autowired
    private JournalLineRepository journalLineRepository;
    @Override
    public List<TrialBalanceView>  getTrialBalance(@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime startDate, @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime endDate) {
        System.out.println(" Right here 1");
        List<TrialBalanceView> views = journalLineRepository.trialBalance(startDate,endDate);
        System.out.println(" Right here 2");
        return views;
    }
}
