package br.com.cominotti.musical_advisor.model.value;

import java.util.Objects;

public class GeoCoordinates {

    private Double latitude;
    private Double longitude;

    public GeoCoordinates(final Double latitude,
                          final Double longitude) {
        Objects.requireNonNull(latitude);
        Objects.requireNonNull(longitude);

        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeoCoordinates that = (GeoCoordinates) o;
        return Objects.equals(latitude, that.latitude) &&
                Objects.equals(longitude, that.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }
}
