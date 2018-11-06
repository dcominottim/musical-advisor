package br.com.cominotti.musical_advisor.model.value;

import java.util.Objects;

public class Temperature {

    private TemperatureValue value;

    public Temperature(final TemperatureValue value) {
        Objects.requireNonNull(value);

        this.value = value;
    }

    public CelsiusValue inCelsiusDegrees() {
        return CelsiusValue.class.isAssignableFrom(value.getClass())
                ? (CelsiusValue) value
                : new CelsiusValue(
                        (value.getValue() - 32) / 1.8
                );
    }

    public FahrenheitValue inFahrenheitDegrees() {
        return FahrenheitValue.class.isAssignableFrom(value.getClass())
                ? (FahrenheitValue) value
                : new FahrenheitValue(
                        value.getValue() * 1.8 + 32
                );
    }

    public boolean isLowerThan(final Temperature other) {
        Objects.requireNonNull(other);

        return this.inCelsiusDegrees().getValue() < other.inCelsiusDegrees().getValue();
    }

    public boolean isHigherThan(final Temperature other) {
        Objects.requireNonNull(other);

        return this.inCelsiusDegrees().getValue() > other.inCelsiusDegrees().getValue();
    }

    public boolean isEqualTo(final Temperature other) {
        Objects.requireNonNull(other);

        return this.inCelsiusDegrees().getValue().equals(other.inCelsiusDegrees().getValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Temperature that = (Temperature) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
