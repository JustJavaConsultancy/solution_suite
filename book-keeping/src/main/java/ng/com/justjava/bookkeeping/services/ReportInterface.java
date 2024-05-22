package ng.com.justjava.bookkeeping.services;

import ng.com.justjava.bookkeeping.db.view.TrialBalanceView;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

public interface ReportInterface {
    public List<TrialBalanceView> getTrialBalance(@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime startDate,
                                                  @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime endDate);
}
