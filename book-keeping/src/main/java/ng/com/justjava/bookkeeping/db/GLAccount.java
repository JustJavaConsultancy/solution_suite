package ng.com.justjava.bookkeeping.db;

import jakarta.persistence.*;
import lombok.*;
import ng.com.justjava.bookkeeping.db.entityListener.GLAccountListener;
import ng.com.justjava.bookkeeping.db.valueObject.Money;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.LinkedHashSet;
import java.util.Set;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EntityListeners(GLAccountListener.class)
@Entity
public class GLAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    String code;
    String name;
    @JdbcTypeCode(SqlTypes.JSON)
    Money balance;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private GLAccount parent;

    @OneToMany(mappedBy = "parent", orphanRemoval = true)
    private Set<GLAccount> children = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "chart_of_account_id")
    private ChartOfAccount chartOfAccount;


    @JdbcTypeCode(SqlTypes.JSON)
    Money budgeted;
    String description;

    @ManyToOne
    @JoinColumn(name = "gl_header_id")
    private GLHeader glHeader;

    public void addChild(GLAccount child){
        if(children==null)
            children = new LinkedHashSet<>();

        children.add(child);
    }
    public Money getTotalAmount(){
        Money result = getBalance();
        System.out.println( " Get Balance===="+getBalance());
        System.out.println(" Number of children==="+children.size());
        if(children==null)
            return result;


        for (GLAccount child:children) {
            System.out.println(" Adding this==="+child.getBalance());
            result.addMoney(child.getBalance());
        }
        System.out.println(" This is the final result=="+result);
        return result;
    }

/** Header
            -- balance
-- budgeted
-- parent - GLAccount
-- chart - ChartOfAccount*/
}
