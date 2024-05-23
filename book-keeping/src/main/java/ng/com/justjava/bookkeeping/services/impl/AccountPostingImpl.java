package ng.com.justjava.bookkeeping.services.impl;

import ng.com.justjava.bookkeeping.Exception.GLAccountNotFoundException;
import ng.com.justjava.bookkeeping.db.*;
import ng.com.justjava.bookkeeping.db.valueObject.Money;
import ng.com.justjava.bookkeeping.dto.AccountingDetails;
import ng.com.justjava.bookkeeping.dto.TransactionDetails;
import ng.com.justjava.bookkeeping.rest.feignclient.RuleClient;
import ng.com.justjava.bookkeeping.services.AccountPosting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;

@Service
public class AccountPostingImpl implements AccountPosting {

    @Autowired
    private RuleClient ruleClient;
    @Autowired
    private JournalRepository journalRepository;
    @Autowired
    private GLAccountRepository glAccountRepository;
    @Override
    public AccountingDetails post(TransactionDetails transactionDetails) {
        AccountingDetails response = ruleClient.details(transactionDetails);
        if(response==null)
            return AccountingDetails.builder().build();

        return createJournal(response);

    }

    @Override
    public AccountingDetails post(AccountingDetails accountingDetails) {
        return createJournal(accountingDetails);
    }

    private AccountingDetails createJournal(AccountingDetails accountingDetails){
        Journal journal=Journal.builder().dateTimeCreated(LocalDateTime.now())
                .amount(new Money("N",accountingDetails.getTransactionAmount()))
                .organization(null)
                .transReference(String.valueOf(System.nanoTime()))
                .metaData(accountingDetails.getMetData())
                .narration(accountingDetails.getNarration())
                .build();

        HashSet<JournalLine> journalLines = new HashSet<>();


        for (Map line:accountingDetails.getLines()) {
            JournalLine journalLine = new JournalLine();

            System.out.println("String.valueOf(line.get(\"glCode\"))===== "
            + String.valueOf(line.get("glCode")));
            Optional<GLAccount> glAccount = glAccountRepository.findByCode(String.valueOf(line.get("glCode")));
            if (!glAccount.isPresent()){
                throw new GLAccountNotFoundException("GL Account Not Found ("+line.get("glCode")+")");
            }


            journalLine.setCurrentBalance(glAccount.get().getBalance());
            System.out.println(" line.get(\"amount\")===== "+ line.get("amount"));
            Double amount = Double.valueOf(String.valueOf(line.get("amount")));
            //Double amount = (Double) line.get("amount");
            System.out.println(" amount=========="+amount);
            Money transAmount = new Money("N", amount);
            if (String.valueOf(line.get("action")).equalsIgnoreCase("credit")) {
                journalLine.setCredit(transAmount);
                glAccount.get().getBalance().addMoney(transAmount);
            }else {
                journalLine.setDebit(transAmount);
                glAccount.get().getBalance().substractMoney(transAmount);
            }
            journalLine.setGlAccount(glAccount.get());
            journalLine.setComment(String.valueOf(line.get("comment")));
            //journalLines.add(journalLine);
            glAccountRepository.save(glAccount.get());
            journal.addJournalLine(journalLine);
        }
        //journal.setJournalLines(journalLines);
        journalRepository.save(journal);
        return accountingDetails;

    }

}
