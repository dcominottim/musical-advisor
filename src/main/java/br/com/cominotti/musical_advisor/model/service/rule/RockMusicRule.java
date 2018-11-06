package br.com.cominotti.musical_advisor.model.service.rule;

import br.com.cominotti.musical_advisor.model.value.CelsiusValue;
import br.com.cominotti.musical_advisor.model.value.MusicalGenre;
import br.com.cominotti.musical_advisor.model.value.Temperature;

public class RockMusicRule extends MusicalAdvisorAbstractRule {

    public RockMusicRule() {
        super(
                new Temperature(
                        new CelsiusValue(10.0)
                ),
                new Temperature(
                        new CelsiusValue(14.9)
                ),
                MusicalGenre.ROCK
        );
    }
}
