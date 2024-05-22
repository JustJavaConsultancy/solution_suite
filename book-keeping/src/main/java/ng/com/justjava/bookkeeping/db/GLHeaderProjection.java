package ng.com.justjava.bookkeeping.db;

import ng.com.justjava.bookkeeping.db.valueObject.Money;
import org.springframework.data.rest.core.config.Projection;

import java.util.Collection;
import java.util.Set;

@Projection(name = "glHeaderView",types = {GLHeader.class})
public interface GLHeaderProjection {
    Long getId();
    String getCode();
    String getDescription();
    Set<GLAccount> getGAccounts();
    Money getTotalAmount();
}
