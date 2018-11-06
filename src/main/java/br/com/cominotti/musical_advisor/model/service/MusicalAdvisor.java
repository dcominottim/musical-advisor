package br.com.cominotti.musical_advisor.model.service;

import br.com.cominotti.musical_advisor.model.service.rule.MusicalAdvisorRule;
import br.com.cominotti.musical_advisor.model.value.MusicalGenre;
import br.com.cominotti.musical_advisor.model.value.Temperature;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class MusicalAdvisor {

    private final Set<MusicalAdvisorRule> musicalAdvisorRules;

    public MusicalAdvisor(final Collection<MusicalAdvisorRule> musicalAdvisorRules) {
        this.musicalAdvisorRules = new HashSet<>(musicalAdvisorRules);
    }

    public Set<MusicalGenre> evaluate(final Temperature temperature) {
        Objects.requireNonNull(temperature);

        final RuleAccumulator ruleAccumulator = new RuleAccumulator();

        musicalAdvisorRules.forEach(
                rule -> rule.apply(temperature, ruleAccumulator)
        );

        return ruleAccumulator.selectedMusicalCategories;
    }

    private class RuleAccumulator implements MusicalAdvisorRuleAccumulator {

        private final Set<MusicalGenre> selectedMusicalCategories = new HashSet<>();

        @Override
        public void apply(final MusicalGenre musicalGenre) {
            Objects.requireNonNull(musicalGenre);

            selectedMusicalCategories.add(musicalGenre);
        }
    }
}
