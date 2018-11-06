package br.com.cominotti.musical_advisor.infra.adapter.spotify;

import br.com.cominotti.musical_advisor.infra.dto.spotify.SpotifyRecommendationDto;
import br.com.cominotti.musical_advisor.model.value.MusicTitle;
import br.com.cominotti.musical_advisor.model.value.MusicTrack;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DefaultSpotifyRecommendationDtoToMusicTracksAdapter implements SpotifyRecommendationDtoToMusicTracksAdapter {

    @Override
    public Set<MusicTrack> adapt(@NotNull final SpotifyRecommendationDto dto) {
        return dto.getTracks()
                .stream()
                .map(
                        spotifyTrackDto ->
                                new MusicTrack(
                                        new MusicTitle(spotifyTrackDto.getName())
                                )
                )
                .collect(Collectors.toSet());
    }
}
