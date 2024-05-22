package ng.com.justjava.bookkeeping.db;

import ng.com.justjava.db.valueObject.Money;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "JournalineView",types = {JournalLine.class})
public interface JournalLineProjection {
    Long getId();
    Money getCredit();
    Money getDebit();
    Money getCurrentBalance();
    GLAccount getGlAccount();

    @Value("#{target.getJournal().getNarration()}")
    String getNarration();


}
