package br.com.cominotti.musical_advisor.model.value;

import java.util.Objects;

public abstract class TemperatureValue {

    private final Double value;

    public TemperatureValue(final Double value) {
        Objects.requireNonNull(value);

        this.value = value;
    }

    public Double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TemperatureValue that = (TemperatureValue) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
