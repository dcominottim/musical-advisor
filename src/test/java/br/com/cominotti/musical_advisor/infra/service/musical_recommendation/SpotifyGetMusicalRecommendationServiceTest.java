package br.com.cominotti.musical_advisor.infra.service.musical_recommendation;

import br.com.cominotti.musical_advisor.infra.adapter.spotify.SpotifyRecommendationDtoToMusicTracksAdapter;
import br.com.cominotti.musical_advisor.infra.dto.spotify.SpotifyRecommendationDto;
import br.com.cominotti.musical_advisor.infra.dto.spotify.SpotifyTrackDto;
import br.com.cominotti.musical_advisor.model.value.MusicTitle;
import br.com.cominotti.musical_advisor.model.value.MusicTrack;
import br.com.cominotti.musical_advisor.model.value.MusicalGenre;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SpotifyGetMusicalRecommendationServiceTest {

    private static final MusicalGenre musicalGenreFixture = MusicalGenre.CLASSICAL;

    private static final String musicTrack1NameFixture = "Music 1";

    private static final String musicTrack2NameFixture = "Music 2";

    private static final SpotifyRecommendationDto spotifyRecommendationDtoFixture;

    private static final Set<MusicTrack> musicTracksFixture;

    @InjectMocks
    private SpotifyGetMusicalRecommendationService service;

    @Mock
    private SpotifyClient spotifyClient;

    @Mock
    private SpotifyRecommendationDtoToMusicTracksAdapter spotifyRecommendationDtoToMusicTracksAdapter;

    static {
        spotifyRecommendationDtoFixture =
                new SpotifyRecommendationDto(
                        Arrays.asList(
                                new SpotifyTrackDto(musicTrack1NameFixture),
                                new SpotifyTrackDto(musicTrack2NameFixture)
                        )
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
    }

    @Test
    public void testGetMusicalRecommendationReturnsMusicTracks() {
        when(
                spotifyRecommendationDtoToMusicTracksAdapter.adapt(spotifyRecommendationDtoFixture)
        )
        .thenReturn(musicTracksFixture);

        when(
                spotifyClient.getRecommendation(musicalGenreFixture)
        )
        .thenReturn(
                Mono.just(spotifyRecommendationDtoFixture)
        );

        StepVerifier.create(
                service.getMusicalRecommendation(musicalGenreFixture)
        )
        .expectNext(
                musicTracksFixture
        )
        .expectComplete()
        .verify();
    }

    @Test
    public void testGetMusicalRecommendationReturnsEmpty() {
        when(
                spotifyClient.getRecommendation(musicalGenreFixture)
        )
        .thenReturn(Mono.empty());

        StepVerifier.create(
                service.getMusicalRecommendation(musicalGenreFixture)
        )
        .expectNextCount(0)
        .expectComplete()
        .verify();
    }
}