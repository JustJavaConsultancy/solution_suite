package ng.com.justjava.bookkeeping.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public interface GLHeaderRepository extends JpaRepository<ng.com.justjava.bookkeeping.db.GLHeader, Long> {
}