package br.com.cominotti.musical_advisor.infra.adapter.spotify;

import br.com.cominotti.musical_advisor.infra.dto.spotify.SpotifyRecommendationDto;
import br.com.cominotti.musical_advisor.model.value.MusicTrack;

import java.util.Set;

public interface SpotifyRecommendationDtoToMusicTracksAdapter {

    Set<MusicTrack> adapt(SpotifyRecommendationDto dto);
}
