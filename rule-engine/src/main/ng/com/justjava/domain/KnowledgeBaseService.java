package ng.com.justjava.domain;

import ng.com.justjava.db.RulesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.common.base.Enums;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class KnowledgeBaseService {
    @Autowired
    private RulesRepository rulesRepository;

    public List<ng.com.justjava.domain.Rule> getAllRules(){
        return rulesRepository.findAll().stream()
                .map(
                        ruleDbModel -> mapFromDbModel(ruleDbModel)
                )
                .collect(Collectors.toList());
    }

    public List<ng.com.justjava.domain.Rule> getAllRuleByNamespace(String ruleNamespace){
        return rulesRepository.findByRuleNamespace(ruleNamespace).stream()
                .map(
                        ruleDbModel -> mapFromDbModel(ruleDbModel)
                )
                .collect(Collectors.toList());
    }

    private ng.com.justjava.domain.Rule mapFromDbModel(ng.com.justjava.db.RuleDbModel ruleDbModel){
        ng.com.justjava.domain.RuleNamespace namespace = Enums.getIfPresent(ng.com.justjava.domain.RuleNamespace.class, ruleDbModel.getRuleNamespace().toUpperCase())
                .or(ng.com.justjava.domain.RuleNamespace.LOAN);
        return ng.com.justjava.domain.Rule.builder()
                .ruleNamespace(namespace)
                .ruleId(ruleDbModel.getRuleId())
                .condition(ruleDbModel.getCondition())
                .action(ruleDbModel.getAction())
                .description(ruleDbModel.getDescription())
                .priority(ruleDbModel.getPriority())
                .build();
    }
}
