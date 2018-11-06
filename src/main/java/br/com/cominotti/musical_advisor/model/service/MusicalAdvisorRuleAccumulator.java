package br.com.cominotti.musical_advisor.model.service;

import br.com.cominotti.musical_advisor.model.value.MusicalGenre;

public interface MusicalAdvisorRuleAccumulator {

    void apply(MusicalGenre musicalGenre);
}
