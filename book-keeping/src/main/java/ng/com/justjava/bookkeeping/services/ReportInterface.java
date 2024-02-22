package ng.com.justjava.bookkeeping.services;

import ng.com.justjava.bookkeeping.db.view.TrialBalanceView;

import java.util.List;

public interface ReportInterface {
    public List<TrialBalanceView> getTrialBalance();
}
