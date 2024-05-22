package ng.com.justjava.bookkeeping.db;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = "GlAccountView",types = {GLAccount.class})
public interface GlAccountProjection {
    GLHeader getGlHeader();
    GLAccount getParent();
    List<GLAccount> getChildren();
    Long getId();
    String getCode();
    String getName();

    String getDescription();

    @Value("#{target.getBalance().getAmount()}")
    Double getBalance();

    @Value("#{target.getBudgeted().getAmount()}")
    Double getBudgeted();

    @Value("#{target.getChartOfAccount().getOrganization()}")
    Organization getOrganization();


}
