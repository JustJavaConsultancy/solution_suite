package ng.com.justjava.bookkeeping.services;

import ng.com.justjava.ruleEngine.rulesImpl.accountingRule.AccountingDetails;
import ng.com.justjava.ruleEngine.rulesImpl.accountingRule.Line;
import ng.com.justjava.ruleEngine.rulesImpl.accountingRule.TransactionDetails;

public interface AccountPosting {

    public AccountingDetails post(TransactionDetails transactionDetails);
}