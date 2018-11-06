package br.com.cominotti.musical_advisor.model.service.rule;

import br.com.cominotti.musical_advisor.model.service.MusicalAdvisorRuleAccumulator;
import br.com.cominotti.musical_advisor.model.value.Temperature;

public interface MusicalAdvisorRule {

    void apply(Temperature temperature, MusicalAdvisorRuleAccumulator accumulator);
}
