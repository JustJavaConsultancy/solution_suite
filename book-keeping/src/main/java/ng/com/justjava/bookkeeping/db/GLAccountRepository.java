package ng.com.justjava.bookkeeping.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GLAccountRepository extends JpaRepository<GLAccount, Long> {
    Optional<GLAccount> findByCode(String code);
}