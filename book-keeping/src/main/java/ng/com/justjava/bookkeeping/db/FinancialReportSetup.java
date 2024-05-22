package ng.com.justjava.bookkeeping.db;

import jakarta.persistence.*;
import lombok.*;
import ng.com.justjava.bookkeeping.db.entityListener.GLAccountListener;

import java.util.HashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class FinancialReportSetup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String description;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "reportsetup_glaccounts",
            joinColumns = @JoinColumn(name = "setup_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "glaccount_id", referencedColumnName = "id"))
    private Set<GLAccount> glAccounts = new HashSet<>();
}
