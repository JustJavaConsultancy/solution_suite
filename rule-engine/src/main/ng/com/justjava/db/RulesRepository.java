package ng.com.justjava.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RulesRepository extends JpaRepository<ng.com.justjava.db.RuleDbModel, Long> {
    List<ng.com.justjava.db.RuleDbModel> findByRuleNamespace(String ruleNamespace);
    List<ng.com.justjava.db.RuleDbModel> findAll();
}
