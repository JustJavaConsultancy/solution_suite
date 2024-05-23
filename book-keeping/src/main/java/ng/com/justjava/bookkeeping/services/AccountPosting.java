package ng.com.justjava.bookkeeping.services;

import ng.com.justjava.bookkeeping.dto.AccountingDetails;
import ng.com.justjava.bookkeeping.dto.TransactionDetails;

public interface AccountPosting {

    public AccountingDetails post(TransactionDetails transactionDetails);

    public AccountingDetails post(AccountingDetails accountingDetails);
}