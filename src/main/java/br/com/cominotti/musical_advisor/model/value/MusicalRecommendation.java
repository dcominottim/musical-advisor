package br.com.cominotti.musical_advisor.model.value;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class MusicalRecommendation {

    private Set<MusicTrack> tracks;

    public MusicalRecommendation(final Collection<MusicTrack> tracks) {
        this.tracks = new HashSet<>(tracks);
    }

    public Collection<MusicTrack> getTracks() {
        return new HashSet<>(tracks);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MusicalRecommendation that = (MusicalRecommendation) o;
        return Objects.equals(tracks, that.tracks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tracks);
    }
}
