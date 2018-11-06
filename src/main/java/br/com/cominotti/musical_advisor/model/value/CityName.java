package br.com.cominotti.musical_advisor.model.value;

import br.com.cominotti.musical_advisor.model.error.BusinessException;

import java.util.Objects;

public class CityName {

    public static final String NAME_REGEX = "^[\\p{L}\\s]+";

    private String name;

    public CityName(String name) {
        Objects.requireNonNull(name);

        name = name.trim();

        if (!name.matches(NAME_REGEX)) {
            throw new BusinessException("name only accepts letters");
        }

        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityName cityName = (CityName) o;
        return Objects.equals(name, cityName.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
