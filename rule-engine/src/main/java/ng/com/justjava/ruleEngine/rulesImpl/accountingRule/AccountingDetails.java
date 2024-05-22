package ng.com.justjava.ruleEngine.rulesImpl.accountingRule;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AccountingDetails {
    String transactionRef;
    LocalDate dueDate;
    String narration;
    Double transactionAmount;

    Map<String, String> metData;

    public Map<String, String> getMetData() {
        return metData;
    }

    public void setMetData(Map<String, String> metData) {
        this.metData = metData;
    }

    public void setJournalItem(Map<String, Object> journalItem) {
        this.journalItem = journalItem;
        lines.add(journalItem);
    }

    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private Map<String,Object> journalItem;
    List<Map> lines = new ArrayList<>();

    public AccountingDetails(String transactionRef, LocalDate dueDate, String narration, Double transactionAmount, Map<String, Object> journalItem, List<Line> lines) {
        this.transactionRef = transactionRef;
        this.dueDate = dueDate;
        this.narration = narration;
        this.transactionAmount = transactionAmount;
        this.journalItem = journalItem;

    }

    public AccountingDetails() {
    }

    public static AccountingDetailsBuilder builder() {
        return new AccountingDetailsBuilder();
    }

    public Boolean setCredit(Double amount){
        Line line = new Line(amount,0.00, "42442442222" );
        //lines.add(line);
        return true;
    }


    public String getTransactionRef() {
        return this.transactionRef;
    }

    public LocalDate getDueDate() {
        return this.dueDate;
    }

    public String getNarration() {
        return this.narration;
    }

    public Double getTransactionAmount() {
        return this.transactionAmount;
    }

/*    public Map<String, Object> getJournalItem() {
        return this.journalItem;
    }*/

    public List<Map> getLines() {
        return this.lines;
    }

    public void setTransactionRef(String transactionRef) {
        this.transactionRef = transactionRef;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }


    public void setLines(List<Map> lines) {
        this.lines = lines;
    }
    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof AccountingDetails)) return false;
        final AccountingDetails other = (AccountingDetails) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$transactionRef = this.getTransactionRef();
        final Object other$transactionRef = other.getTransactionRef();
        if (this$transactionRef == null ? other$transactionRef != null : !this$transactionRef.equals(other$transactionRef))
            return false;
        final Object this$dueDate = this.getDueDate();
        final Object other$dueDate = other.getDueDate();
        if (this$dueDate == null ? other$dueDate != null : !this$dueDate.equals(other$dueDate)) return false;
        final Object this$narration = this.getNarration();
        final Object other$narration = other.getNarration();
        if (this$narration == null ? other$narration != null : !this$narration.equals(other$narration)) return false;
        final Object this$transactionAmount = this.getTransactionAmount();
        final Object other$transactionAmount = other.getTransactionAmount();
        if (this$transactionAmount == null ? other$transactionAmount != null : !this$transactionAmount.equals(other$transactionAmount))
            return false;
/*        final Object this$journalItem = this.getJournalItem();
        final Object other$journalItem = other.getJournalItem();
        if (this$journalItem == null ? other$journalItem != null : !this$journalItem.equals(other$journalItem))
            return false;*/
        final Object this$lines = this.getLines();
        final Object other$lines = other.getLines();
        if (this$lines == null ? other$lines != null : !this$lines.equals(other$lines)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof AccountingDetails;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $transactionRef = this.getTransactionRef();
        result = result * PRIME + ($transactionRef == null ? 43 : $transactionRef.hashCode());
        final Object $dueDate = this.getDueDate();
        result = result * PRIME + ($dueDate == null ? 43 : $dueDate.hashCode());
        final Object $narration = this.getNarration();
        result = result * PRIME + ($narration == null ? 43 : $narration.hashCode());
        final Object $transactionAmount = this.getTransactionAmount();
        result = result * PRIME + ($transactionAmount == null ? 43 : $transactionAmount.hashCode());
     /*   final Object $journalItem = this.getJournalItem();
        result = result * PRIME + ($journalItem == null ? 43 :
         $journalItem.hashCode());
      */
        final Object $lines = this.getLines();
        result = result * PRIME + ($lines == null ? 43 : $lines.hashCode());
        return result;
    }

    public String toString() {
        return "AccountingDetails(transactionRef=" + this.getTransactionRef() + ", dueDate=" + this.getDueDate() + ", narration=" + this.getNarration() + ", transactionAmount=" + this.getTransactionAmount() + ", journalItem=" + ", lines=" + this.getLines() + ")";
    }

    public static class AccountingDetailsBuilder {
        private String transactionRef;
        private LocalDate dueDate;
        private String narration;
        private Double transactionAmount;
        private Map<String, Object> journalItem;
        private List<Line> lines;

        AccountingDetailsBuilder() {
        }

        public AccountingDetailsBuilder transactionRef(String transactionRef) {
            this.transactionRef = transactionRef;
            return this;
        }

        public AccountingDetailsBuilder dueDate(LocalDate dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public AccountingDetailsBuilder narration(String narration) {
            this.narration = narration;
            return this;
        }

        public AccountingDetailsBuilder transactionAmount(Double transactionAmount) {
            this.transactionAmount = transactionAmount;
            return this;
        }

        public AccountingDetailsBuilder journalItem(Map<String, Object> journalItem) {
            this.journalItem = journalItem;
            return this;
        }

        public AccountingDetailsBuilder lines(List<Line> lines) {
            this.lines = lines;
            return this;
        }

        public AccountingDetails build() {
            return new AccountingDetails(transactionRef, dueDate, narration, transactionAmount, journalItem, lines);
        }

        public String toString() {
            return "AccountingDetails.AccountingDetailsBuilder(transactionRef=" + this.transactionRef + ", dueDate=" + this.dueDate + ", narration=" + this.narration + ", transactionAmount=" + this.transactionAmount + ", journalItem=" + this.journalItem + ", lines=" + this.lines + ")";
        }
    }
}
