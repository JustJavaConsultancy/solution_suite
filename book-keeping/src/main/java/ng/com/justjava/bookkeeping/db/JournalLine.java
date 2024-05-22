package ng.com.justjava.bookkeeping.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import ng.com.justjava.bookkeeping.db.valueObject.Money;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.rest.core.annotation.RestResource;

import java.time.LocalDateTime;

//@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class JournalLine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    Long id;
    @JdbcTypeCode(SqlTypes.JSON)
    Money credit;

    @JdbcTypeCode(SqlTypes.JSON)
    Money debit;

    @JdbcTypeCode(SqlTypes.JSON)
    Money currentBalance;

    @ManyToOne
    @JoinColumn(name = "gl_account_id", nullable = false)
    private GLAccount glAccount;

    @ManyToOne
    @JoinColumn(name = "journal_id", nullable = false)
    private Journal journal;

    private String comment;

    public String getNarration(){
        return journal.getNarration();
    }
    public String getReference(){
        return journal.getTransReference();
    }
    public LocalDateTime getLocalDateTime(){
        return journal.getDateTimeCreated();
    }
}
