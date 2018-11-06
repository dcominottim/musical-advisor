package br.com.cominotti.musical_advisor.infra.dto;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class MusicalRecommendationDto {

    private Set<MusicTrackDto> tracks;

    public MusicalRecommendationDto(final Collection<MusicTrackDto> tracks) {
        this.tracks = new HashSet<>(tracks);
    }

    public Collection<MusicTrackDto> getTracks() {
        return new HashSet<>(tracks);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MusicalRecommendationDto that = (MusicalRecommendationDto) o;
        return Objects.equals(tracks, that.tracks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tracks);
    }
}
