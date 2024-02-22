package ng.com.justjava.bookkeeping.db;

import ng.com.justjava.bookkeeping.db.view.TrialBalanceView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

public interface JournalLineRepository extends JpaRepository<JournalLine, Long> {
    @Query("""
            select j from JournalLine j
            where j.glAccount.code = ?1 and j.journal.dateTimeCreated between ?2 and ?3
            order by j.journal.dateTimeCreated""")
    @RestResource(path = "statment", rel = "glStatement")
    List<JournalLine> findByGlAccount_CodeAndJournal_DateTimeCreatedBetweenOrderByJournal_DateTimeCreatedAsc(String code, @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime dateTimeCreatedStart, @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime dateTimeCreatedEnd);

    @Query(value = """
select j.gl_account_id as id,g.name as name, (sum(cast(j.credit -> 'amount' as float)) -
       sum(cast(j.debit -> 'amount' as float)) ) as balance
from journal_line j inner join glaccount g on j.gl_account_id=g.id
group by j.gl_account_id, g.name """,nativeQuery = true)
    public List<TrialBalanceView> trialBalance();


}