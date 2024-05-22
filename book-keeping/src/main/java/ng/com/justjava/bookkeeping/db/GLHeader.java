package ng.com.justjava.bookkeeping.db;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ng.com.justjava.bookkeeping.db.valueObject.Money;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class GLHeader {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String code;
    private String description;
    @OneToMany(mappedBy = "glHeader")
    private Set<GLAccount> gAccounts = new LinkedHashSet<>();


    public Money getTotalAmount(){

        Double sum = 0.00;
        Money result = new Money();
        if(gAccounts == null || gAccounts.isEmpty())
            return new Money();
        for (GLAccount gl:gAccounts) {
            result.addMoney(gl.getBalance());
        }
        return result;
    }

}
