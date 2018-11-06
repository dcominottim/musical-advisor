package br.com.cominotti.musical_advisor.app.service;

import br.com.cominotti.musical_advisor.model.value.MusicTrack;
import br.com.cominotti.musical_advisor.model.value.MusicalGenre;
import reactor.core.publisher.Mono;

import java.util.Set;

public interface GetMusicalRecommendationService {

    Mono<Set<MusicTrack>> getMusicalRecommendation(MusicalGenre musicalGenre);
}
