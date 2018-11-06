package br.com.cominotti.musical_advisor.model.service.rule;

import br.com.cominotti.musical_advisor.model.value.CelsiusValue;
import br.com.cominotti.musical_advisor.model.value.MusicalGenre;
import br.com.cominotti.musical_advisor.model.value.Temperature;

public class PopMusicRule extends MusicalAdvisorAbstractRule {

    public PopMusicRule() {
        super(
                new Temperature(
                        new CelsiusValue(15.0)
                ),
                new Temperature(
                        new CelsiusValue(30.9)
                ),
                MusicalGenre.POP
        );
    }
}
