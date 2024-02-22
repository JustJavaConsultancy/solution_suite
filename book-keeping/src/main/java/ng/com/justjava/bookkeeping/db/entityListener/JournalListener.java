package ng.com.justjava.bookkeeping.db.entityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.PrePersist;
import ng.com.justjava.bookkeeping.db.Journal;


public class JournalListener {
    @PrePersist
    public void persist(Journal journal){
        journal.setTransReference(journal.getId()+"_"+journal.getTransReference());
        System.out.println(" Persisting Journal Here=="+journal.getId());
    }
}
