package ng.com.justjava.bookkeeping.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;

public interface JournalRepository extends JpaRepository<Journal, Long> {
    @RestResource(path = "byRef", rel = "byRef")
    @Query("select j from Journal j where j.transReference = ?1")
    Optional<Journal> findByTransReference(String transReference);

}