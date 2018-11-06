package br.com.cominotti.musical_advisor.infra.dto.open_weather_maps;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class OpenWeatherMapsCityMainDto {

    private Double temp;

    @JsonCreator
    public OpenWeatherMapsCityMainDto(@JsonProperty("temp") final Double temp) {
        this.temp = temp;
    }

    public Double getTemp() {
        return temp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OpenWeatherMapsCityMainDto main = (OpenWeatherMapsCityMainDto) o;
        return Objects.equals(temp, main.temp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(temp);
    }
}
