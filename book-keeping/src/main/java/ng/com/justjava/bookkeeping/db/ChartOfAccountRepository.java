package ng.com.justjava.bookkeeping.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;

public interface ChartOfAccountRepository extends JpaRepository<ChartOfAccount, Long> {
    @Query("select c from ChartOfAccount c where c.organization.code = ?1")
    @RestResource(path = "byorganization", rel = "byorganization")
    Optional<ChartOfAccount> findByOrganization_Code(String code);

}