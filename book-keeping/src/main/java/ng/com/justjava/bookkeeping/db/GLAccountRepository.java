package ng.com.justjava.bookkeeping.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(excerptProjection = GlAccountProjection.class)
public interface GLAccountRepository extends JpaRepository<GLAccount, Long> {
    Optional<GLAccount> findByCode(String code);

     @RestResource(path = "byorganization", rel = "byorganization")
     List<GLAccount> findByChartOfAccount_Organization_Code(String code);

}