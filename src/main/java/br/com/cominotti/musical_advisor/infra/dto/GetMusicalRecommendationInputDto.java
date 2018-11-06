package br.com.cominotti.musical_advisor.infra.dto;

import br.com.cominotti.musical_advisor.model.error.BusinessException;
import br.com.cominotti.musical_advisor.model.value.CityName;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Pattern;
import java.util.Objects;

public class GetMusicalRecommendationInputDto {

    @Pattern(
            message = "Only letters allowed",
            regexp = CityName.NAME_REGEX
    )
    private final String cityName;

    private final Double latitude;

    private final Double longitude;

    @JsonCreator
    public GetMusicalRecommendationInputDto(@JsonProperty("cityName") final String cityName,
                                            @JsonProperty("latitude") final Double latitude,
                                            @JsonProperty("longitude") final Double longitude) {
        if (cityName == null && (latitude == null || longitude == null)) {
            throw new BusinessException("latitude and longitude cannot be null if cityName is not specified");
        }

        this.cityName = cityName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getCityName() {
        return cityName;
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
        GetMusicalRecommendationInputDto that = (GetMusicalRecommendationInputDto) o;
        return Objects.equals(cityName, that.cityName) &&
                Objects.equals(latitude, that.latitude) &&
                Objects.equals(longitude, that.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityName, latitude, longitude);
    }
}
