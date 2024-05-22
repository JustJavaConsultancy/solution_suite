package ng.com.justjava.bookkeeping.db.valueObject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Money {
    String currency;
    Double amount=0.00;

    public Double addMoney(Money money){
/*
        if(this.amount==null)
            amount=0.00;
*/

        amount=amount+money.getAmount();
        return amount;
    }
    public void substractMoney(Money money){
/*        if(this.amount==null)
            amount=0.00;*/

        amount=amount-money.getAmount();
    }

}
