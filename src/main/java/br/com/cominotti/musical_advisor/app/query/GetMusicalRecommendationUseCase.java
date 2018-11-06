package br.com.cominotti.musical_advisor.app.query;

import br.com.cominotti.musical_advisor.app.service.GetCityTemperatureService;
import br.com.cominotti.musical_advisor.app.service.GetMusicalRecommendationService;
import br.com.cominotti.musical_advisor.model.service.MusicalAdvisor;
import br.com.cominotti.musical_advisor.model.service.rule.ClassicalMusicRule;
import br.com.cominotti.musical_advisor.model.service.rule.PartyMusicRule;
import br.com.cominotti.musical_advisor.model.service.rule.PopMusicRule;
import br.com.cominotti.musical_advisor.model.service.rule.RockMusicRule;
import br.com.cominotti.musical_advisor.model.value.MusicTrack;
import br.com.cominotti.musical_advisor.model.value.MusicalGenre;
import br.com.cominotti.musical_advisor.model.value.MusicalRecommendation;
import br.com.cominotti.musical_advisor.model.value.Temperature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GetMusicalRecommendationUseCase implements GetMusicalRecommendationQuery {

    private final GetCityTemperatureService getCityTemperatureService;

    private final GetMusicalRecommendationService getMusicalRecommendationService;

    @Autowired
    public GetMusicalRecommendationUseCase(final GetCityTemperatureService getCityTemperatureService,
                                           final GetMusicalRecommendationService getMusicalRecommendationService) {
        this.getCityTemperatureService = getCityTemperatureService;
        this.getMusicalRecommendationService = getMusicalRecommendationService;
    }

    @Override
    public Mono<Output> run(@NotNull final Input input) {
        final Mono<Temperature> targetCityTemperatureMono =
                input.getCityName() != null
                        ? getCityTemperatureService.getCityTemperature(
                                input.getCityName()
                        )
                        : getCityTemperatureService.getCityTemperature(
                                input.getGeoCoordinates()
                        );

        return targetCityTemperatureMono.flatMap(
                targetCityTemperature -> {
                    final MusicalAdvisor musicalAdvisor =
                            new MusicalAdvisor(
                                    Arrays.asList(
                                            new ClassicalMusicRule(),
                                            new PartyMusicRule(),
                                            new PopMusicRule(),
                                            new RockMusicRule()
                                    )
                            );

                    final Set<MusicalGenre> musicalGenresForTargetCityTemperature =
                            musicalAdvisor.evaluate(targetCityTemperature);

                    return Mono.zip(
                            musicalGenresForTargetCityTemperature.parallelStream()
                                    .map(
                                            getMusicalRecommendationService::getMusicalRecommendation
                                    ).collect(Collectors.toSet()),
                            combinator ->
                                    Arrays.stream(combinator).flatMap(
                                            item -> ((Set<MusicTrack>) item).stream()
                                    ).collect(
                                            Collectors.toCollection(HashSet::new)
                                    )
                    ).map(
                            musicTracks ->
                                    new Output(
                                            new MusicalRecommendation(musicTracks)
                                    )
                    );
                }
        ).switchIfEmpty(
                Mono.just(
                        new Output(
                                new MusicalRecommendation(Collections.emptySet())
                        )
                )
        );
    }
}
