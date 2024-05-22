package ng.com.justjava.domain;

import com.google.common.base.Enums;
import ng.com.justjava.db.RuleDbModel;
import ng.com.justjava.db.RulesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KnowledgeBaseService {
    @Autowired
    private RulesRepository rulesRepository;


    public Rule saveRule(Rule rule){
        RuleDbModel ruleDbModel = mapFromModel(rule);
        rule=mapFromDbModel(rulesRepository.save(ruleDbModel));
        return rule;
    }
    public List<Rule> getAllRules(){
        return rulesRepository.findAll().stream()
                .map(
                        ruleDbModel -> mapFromDbModel(ruleDbModel)
                )
                .collect(Collectors.toList());
    }

    public List<Rule> getAllRuleByNamespace(String ruleNamespace){
        return rulesRepository.findByRuleNamespace(ruleNamespace).stream()
                .map(
                        ruleDbModel -> mapFromDbModel(ruleDbModel)
                )
                .collect(Collectors.toList());
    }
    public List<Rule> getAllRuleByOrg(String org){
        return rulesRepository.findByOrgCode(org).stream()
                .map(
                        ruleDbModel -> mapFromDbModel(ruleDbModel)
                )
                .collect(Collectors.toList());
    }
    private Rule mapFromDbModel(RuleDbModel ruleDbModel){
        RuleNamespace namespace = Enums.getIfPresent(RuleNamespace.class, ruleDbModel.getRuleNamespace().toUpperCase())
                .or(RuleNamespace.LOAN);
        return Rule.builder()
                .orgCode(ruleDbModel.getOrgCode())
                .ruleNamespace(namespace)
                //.ruleId(ruleDbModel.getRuleId())
                .condition(ruleDbModel.getCondition())
                .action(ruleDbModel.getAction())
                .description(ruleDbModel.getDescription())
                .priority(ruleDbModel.getPriority())
                .build();
    }
    private RuleDbModel mapFromModel(Rule rule){
        RuleNamespace namespace = rule.getRuleNamespace();
        return RuleDbModel.builder()
                .orgCode(rule.getOrgCode())
                .ruleNamespace(String.valueOf(namespace))
                //.ruleId(rule.getRuleId())
                .condition(rule.getCondition())
                .action(rule.getAction())
                .description(rule.getDescription())
                .priority(rule.getPriority())
                .build();
    }

}
