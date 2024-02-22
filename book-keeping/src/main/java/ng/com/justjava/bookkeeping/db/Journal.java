package ng.com.justjava.bookkeeping.db;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ng.com.justjava.bookkeeping.db.entityListener.JournalListener;
import ng.com.justjava.bookkeeping.db.valueObject.Money;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(JournalListener.class)
@Entity
public class Journal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    String narration;
    @JdbcTypeCode(SqlTypes.JSON)
    Money amount;

    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    LocalDateTime dateTimeCreated;
    String transReference;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "organization_id")
    Organization organization;


    @OneToMany(mappedBy = "journal", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<JournalLine> journalLines = new LinkedHashSet<>();

    public void addJournalLine(JournalLine journalLine){
        journalLine.setJournal(this);
        if(journalLines==null)
            journalLines = new LinkedHashSet<>();

        journalLines.add(journalLine);
    }
}
