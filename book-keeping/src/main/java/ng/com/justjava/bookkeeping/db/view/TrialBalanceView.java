package ng.com.justjava.bookkeeping.db.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ng.com.justjava.bookkeeping.db.JournalLine;
import org.springframework.data.rest.core.config.Projection;

/*@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor*/
public interface TrialBalanceView {
    Long getId();
    String getName();
    Double getBalance();
}
