package br.com.cominotti.musical_advisor.model.service.rule;

import br.com.cominotti.musical_advisor.model.error.BusinessException;
import br.com.cominotti.musical_advisor.model.service.MusicalAdvisorRuleAccumulator;
import br.com.cominotti.musical_advisor.model.value.MusicalGenre;
import br.com.cominotti.musical_advisor.model.value.Temperature;

import java.util.Objects;

public abstract class MusicalAdvisorAbstractRule implements MusicalAdvisorRule {

    private final Temperature lowerBoundTemperature;
    private final Temperature upperBoundTemperature;
    private final MusicalGenre musicalGenreToIncludeIfTemperatureIsWithinBounds;

    public MusicalAdvisorAbstractRule(final Temperature lowerBoundTemperature,
                                      final Temperature upperBoundTemperature,
                                      final MusicalGenre musicalGenreToIncludeIfTemperatureIsWithinBounds) {
        Objects.requireNonNull(lowerBoundTemperature);
        Objects.requireNonNull(upperBoundTemperature);
        Objects.requireNonNull(musicalGenreToIncludeIfTemperatureIsWithinBounds);

        if (lowerBoundTemperature.isHigherThan(upperBoundTemperature)) {
            throw new BusinessException("lowerBoundTemperature must be lower than upperBoundTemperature");
        }

        this.lowerBoundTemperature = lowerBoundTemperature;
        this.upperBoundTemperature = upperBoundTemperature;
        this.musicalGenreToIncludeIfTemperatureIsWithinBounds = musicalGenreToIncludeIfTemperatureIsWithinBounds;
    }

    @Override
    public void apply(final Temperature temperature,
                      final MusicalAdvisorRuleAccumulator accumulator) {
        Objects.requireNonNull(temperature);
        Objects.requireNonNull(accumulator);

        if (
                temperature.isLowerThan(lowerBoundTemperature) ||
                temperature.isHigherThan(upperBoundTemperature)
        ) {
            return;
        }

        accumulator.apply(musicalGenreToIncludeIfTemperatureIsWithinBounds);
    }
}
