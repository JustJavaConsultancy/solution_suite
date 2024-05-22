package ng.com.justjava.bookkeeping.db;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import ng.com.justjava.bookkeeping.db.valueObject.Money;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
//@RepositoryRestResource(excerptProjection = JournalProjection.class)
@Projection(name = "JournalView",types = {Journal.class})
public interface JournalProjection {
    String getNarration();
    Money getAmount();
    LocalDateTime getDateTimeCreated();
    String getTransReference();
    Organization getOrganization();
    Set<JournalLine> getJournalLines();

}
