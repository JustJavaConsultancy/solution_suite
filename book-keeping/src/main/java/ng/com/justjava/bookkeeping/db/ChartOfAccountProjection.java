package ng.com.justjava.bookkeeping.db;

import org.springframework.data.rest.core.config.Projection;

import java.util.Set;

@Projection(name = "chartofAccountView",types = {ChartOfAccount.class})
public interface ChartOfAccountProjection {
    Long getId();
    Organization getOrganization();
    Set<GLAccount> getGAccounts();

}
