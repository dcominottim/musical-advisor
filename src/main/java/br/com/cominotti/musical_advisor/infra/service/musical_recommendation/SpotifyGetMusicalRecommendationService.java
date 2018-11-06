package br.com.cominotti.musical_advisor.infra.service.musical_recommendation;

import br.com.cominotti.musical_advisor.app.service.GetMusicalRecommendationService;
import br.com.cominotti.musical_advisor.infra.adapter.spotify.SpotifyRecommendationDtoToMusicTracksAdapter;
import br.com.cominotti.musical_advisor.model.value.MusicTrack;
import br.com.cominotti.musical_advisor.model.value.MusicalGenre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Service
public class SpotifyGetMusicalRecommendationService implements GetMusicalRecommendationService {

    private final SpotifyClient spotifyClient;

    private final SpotifyRecommendationDtoToMusicTracksAdapter spotifyRecommendationDtoToMusicTrackAdapter;

    @Autowired
    public SpotifyGetMusicalRecommendationService(final SpotifyClient spotifyClient,
                                                  final SpotifyRecommendationDtoToMusicTracksAdapter spotifyRecommendationDtoToMusicTracksAdapter) {
        this.spotifyClient = spotifyClient;
        this.spotifyRecommendationDtoToMusicTrackAdapter = spotifyRecommendationDtoToMusicTracksAdapter;
    }

    @Override
    public Mono<Set<MusicTrack>> getMusicalRecommendation(@NotNull final MusicalGenre musicalGenre) {
        return spotifyClient.getRecommendation(musicalGenre)
                .flatMap(
                        spotifyRecommendationDto ->
                                Mono.just(
                                        spotifyRecommendationDtoToMusicTrackAdapter.adapt(spotifyRecommendationDto)
                                )
                );
    }
}
