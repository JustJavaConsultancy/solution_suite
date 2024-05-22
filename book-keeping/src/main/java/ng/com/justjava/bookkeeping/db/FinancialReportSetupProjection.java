package ng.com.justjava.bookkeeping.db;


import org.springframework.data.rest.core.config.Projection;

@Projection(name = "FinancialReportSetupView",types = {FinancialReportSetup.class})
public interface FinancialReportSetupProjection {
    String getName();
    String getDescription();
}
