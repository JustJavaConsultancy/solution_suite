package ng.com.justjava.bookkeeping.db;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ChartOfAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @OneToMany(mappedBy = "chartOfAccount")
    private Set<GLAccount> gAccounts = new LinkedHashSet<>();

/*    @PrePersist
    public void addChatToOrganization(){

        System.out.println(" Here is the pre persist firing==="+this.getOrganization().code);
        organization.setOrgChartOfAccount(this);
    }*/

}
