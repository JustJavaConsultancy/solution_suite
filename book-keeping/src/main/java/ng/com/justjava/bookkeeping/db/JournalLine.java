package ng.com.justjava.bookkeeping.db;

import jakarta.persistence.*;
import lombok.*;
import ng.com.justjava.bookkeeping.db.valueObject.Money;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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

}
