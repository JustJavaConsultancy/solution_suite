package ng.com.justjava.bookkeeping.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = FinancialReportSetupProjection.class)
public interface FinancialReportSetupRepository extends JpaRepository<FinancialReportSetup, Long> {
}
