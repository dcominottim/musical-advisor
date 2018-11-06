package br.com.cominotti.musical_advisor.app.query;

import br.com.cominotti.musical_advisor.app.service.GetCityTemperatureService;
import br.com.cominotti.musical_advisor.app.service.GetMusicalRecommendationService;
import br.com.cominotti.musical_advisor.model.value.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetMusicalRecommendationUseCaseTest {

    private static final MusicalGenre classicalMusicalGenreFixture = MusicalGenre.CLASSICAL;

    private static final CityName campinasCityNameFixture;

    private static final GeoCoordinates geoCoordinatesFixture;

    private static final Temperature temperatureOf9DegreesCelsiusFixture;

    private static final String musicTrack1NameFixture = "Music 1";

    private static final String musicTrack2NameFixture = "Music 2";

    private static final Set<MusicTrack> musicTracksFixture;

    private static final GetMusicalRecommendationQuery.Input useCaseInputWithCityNameFixture;

    private static final GetMusicalRecommendationQuery.Input useCaseInputWithGeoCoordinatesFixture;

    private static final MusicalRecommendation musicalRecommendationFixture;

    private static final GetMusicalRecommendationQuery.Output useCaseOutputWithMusicalRecommendationFixture;

    private static final GetMusicalRecommendationQuery.Output getUseCaseOutputWithEmptyMusicalRecommendationFixture;

    @InjectMocks
    private GetMusicalRecommendationUseCase useCase;

    @Mock
    private GetCityTemperatureService getCityTemperatureService;

    @Mock
    private GetMusicalRecommendationService getMusicalRecommendationService;

    static {
        campinasCityNameFixture = new CityName("Campinas");

        geoCoordinatesFixture = new GeoCoordinates(100.0, 100.0);

        temperatureOf9DegreesCelsiusFixture =
                new Temperature(
                        new CelsiusValue(9.0)
                );

        musicTracksFixture =
                new HashSet<>(
                        Arrays.asList(
                                new MusicTrack(
                                        new MusicTitle(musicTrack1NameFixture)
                                ),
                                new MusicTrack(
                                        new MusicTitle(musicTrack2NameFixture)
                                )
                        )
                );

        useCaseInputWithCityNameFixture =
                new GetMusicalRecommendationQuery.Input(campinasCityNameFixture);

        useCaseInputWithGeoCoordinatesFixture =
                new GetMusicalRecommendationQuery.Input(geoCoordinatesFixture);

        musicalRecommendationFixture =
                new MusicalRecommendation(
                        Arrays.asList(
                                new MusicTrack(
                                        new MusicTitle(musicTrack1NameFixture)
                                ),
                                new MusicTrack(
                                        new MusicTitle(musicTrack2NameFixture)
                                )
                        )
                );

        useCaseOutputWithMusicalRecommendationFixture =
                new GetMusicalRecommendationQuery.Output(musicalRecommendationFixture);

        getUseCaseOutputWithEmptyMusicalRecommendationFixture =
                new GetMusicalRecommendationQuery.Output(
                        new MusicalRecommendation(
                                Collections.emptySet()
                        )
                );
    }

    @Test
    public void testRunReturnsOutputForInputWithCityName() {
        when(
                getCityTemperatureService.getCityTemperature(campinasCityNameFixture)
        )
        .thenReturn(
                Mono.just(temperatureOf9DegreesCelsiusFixture)
        );

        when(
                getMusicalRecommendationService.getMusicalRecommendation(classicalMusicalGenreFixture)
        )
        .thenReturn(
                Mono.just(musicTracksFixture)
        );

        StepVerifier.create(
                useCase.run(useCaseInputWithCityNameFixture)
        )
        .expectNext(
                useCaseOutputWithMusicalRecommendationFixture
        )
        .expectComplete()
        .verify();
    }

    @Test
    public void testRunReturnsOutputForInputWithGeoCoordinates() {
        when(
                getCityTemperatureService.getCityTemperature(geoCoordinatesFixture)
        )
        .thenReturn(
                Mono.just(temperatureOf9DegreesCelsiusFixture)
        );

        when(
                getMusicalRecommendationService.getMusicalRecommendation(classicalMusicalGenreFixture)
        )
        .thenReturn(
                Mono.just(musicTracksFixture)
        );

        StepVerifier.create(
                useCase.run(useCaseInputWithGeoCoordinatesFixture)
        )
        .expectNext(
                useCaseOutputWithMusicalRecommendationFixture
        )
        .expectComplete()
        .verify();
    }

    @Test
    public void testRunReturnsOutputWithEmptyMusicalRecommendation() {
        when(
                getCityTemperatureService.getCityTemperature(campinasCityNameFixture)
        )
        .thenReturn(
                Mono.empty()
        );

        StepVerifier.create(
                useCase.run(useCaseInputWithCityNameFixture)
        )
        .expectNext(
                getUseCaseOutputWithEmptyMusicalRecommendationFixture
        )
        .expectComplete()
        .verify();
    }
}