package br.com.cominotti.musical_advisor.model.value;

import br.com.cominotti.musical_advisor.model.error.BusinessException;

import java.util.Objects;

public class MusicTitle {

    private String title;

    public MusicTitle(String title) {
        Objects.requireNonNull(title);

        title = title.trim();

        if (title.isEmpty() || title.isBlank()) {
            throw new BusinessException("title cannot be empty or blank");
        }

        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MusicTitle that = (MusicTitle) o;
        return Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
