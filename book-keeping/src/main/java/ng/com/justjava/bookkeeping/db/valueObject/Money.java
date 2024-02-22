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
    Double amount;

    public Double addMoney(Money money){
        amount=amount+money.getAmount();
        return amount;
    }
    public void substractMoney(Money money){
        amount=amount-money.getAmount();
    }

}
