package br.com.cominotti.musical_advisor.model.service.rule;

import br.com.cominotti.musical_advisor.model.value.CelsiusValue;
import br.com.cominotti.musical_advisor.model.value.MusicalGenre;
import br.com.cominotti.musical_advisor.model.value.Temperature;

public class PartyMusicRule extends MusicalAdvisorAbstractRule {

    public PartyMusicRule() {
        super(
                new Temperature(
                        new CelsiusValue(31.0)
                ),
                new Temperature(
                        new CelsiusValue(Double.MAX_VALUE)
                ),
                MusicalGenre.PARTY
        );
    }
}
