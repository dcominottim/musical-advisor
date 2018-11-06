package br.com.cominotti.musical_advisor.infra.dto.spotify;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class SpotifyRecommendationDto {

    private Set<SpotifyTrackDto> tracks;

    @JsonCreator
    public SpotifyRecommendationDto(@JsonProperty("tracks") final Collection<SpotifyTrackDto> tracks) {
        this.tracks = new HashSet<>(tracks);
    }

    public Set<SpotifyTrackDto> getTracks() {
        return new HashSet<>(tracks);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpotifyRecommendationDto that = (SpotifyRecommendationDto) o;
        return Objects.equals(tracks, that.tracks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tracks);
    }
}
