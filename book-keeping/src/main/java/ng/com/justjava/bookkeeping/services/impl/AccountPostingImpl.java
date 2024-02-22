package ng.com.justjava.bookkeeping.services.impl;

import ng.com.justjava.bookkeeping.db.*;
import ng.com.justjava.bookkeeping.db.valueObject.Money;
import ng.com.justjava.bookkeeping.services.AccountPosting;
import ng.com.justjava.ruleEngine.rulesImpl.accountingRule.AccountingDetails;
import ng.com.justjava.ruleEngine.rulesImpl.accountingRule.TransactionDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
public class AccountPostingImpl implements AccountPosting {

    @Autowired
    private JournalRepository journalRepository;
    @Autowired
    private GLAccountRepository glAccountRepository;
    @Override
    public AccountingDetails post(TransactionDetails transactionDetails) {
        RestClient restClient = RestClient.create();
        AccountingDetails response = restClient.post()
                .uri("http://localhost:8081/accounting")
                .contentType(APPLICATION_JSON)
                .body(transactionDetails)
                .retrieve()
                .body(AccountingDetails.class);
        System.out.println(" The response content  :"+response);
        Journal journal=Journal.builder().dateTimeCreated(LocalDateTime.now())
                .amount(new Money("N",response.getTransactionAmount()))
                .organization(null)
                .transReference(String.valueOf(System.nanoTime()))
                .narration(response.getNarration())
                .build();

        HashSet<JournalLine> journalLines = new HashSet<>();


        for (Map line:response.getLines()) {
            JournalLine journalLine = new JournalLine();
            Optional<GLAccount> glAccount = glAccountRepository.findByCode(String.valueOf(line.get("glCode")));
            journalLine.setCurrentBalance(glAccount.get().getBalance());
            System.out.println(" line.get(\"amount\")===== "+ line.get("amount"));
            Double amount = (Double) line.get("amount");
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
            //journalLines.add(journalLine);
            glAccountRepository.save(glAccount.get());
            journal.addJournalLine(journalLine);
        }
        //journal.setJournalLines(journalLines);
        journalRepository.save(journal);
        return response;

    }
}
