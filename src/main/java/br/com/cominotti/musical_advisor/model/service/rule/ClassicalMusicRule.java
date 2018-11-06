package br.com.cominotti.musical_advisor.model.service.rule;

import br.com.cominotti.musical_advisor.model.value.CelsiusValue;
import br.com.cominotti.musical_advisor.model.value.MusicalGenre;
import br.com.cominotti.musical_advisor.model.value.Temperature;

public class ClassicalMusicRule extends MusicalAdvisorAbstractRule {

    public ClassicalMusicRule() {
        super(
                new Temperature(
                        new CelsiusValue(Double.MIN_VALUE)
                ),
                new Temperature(
                        new CelsiusValue(9.9)
                ),
                MusicalGenre.CLASSICAL
        );
    }
}
