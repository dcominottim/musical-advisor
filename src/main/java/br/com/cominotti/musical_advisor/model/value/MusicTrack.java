package br.com.cominotti.musical_advisor.model.value;

import java.util.Objects;

public class MusicTrack {

    private final MusicTitle title;

    public MusicTrack(final MusicTitle title) {
        Objects.requireNonNull(title);

        this.title = title;
    }

    public MusicTitle getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MusicTrack that = (MusicTrack) o;
        return Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
