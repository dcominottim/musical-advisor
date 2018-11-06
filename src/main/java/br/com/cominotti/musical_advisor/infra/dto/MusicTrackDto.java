package br.com.cominotti.musical_advisor.infra.dto;

import java.util.Objects;

public class MusicTrackDto {

    private final String title;

    public MusicTrackDto(final String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MusicTrackDto that = (MusicTrackDto) o;
        return Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
