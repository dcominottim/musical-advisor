package br.com.cominotti.musical_advisor.infra.dto.open_weather_maps;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class OpenWeatherMapsCityDto {
    private final Integer id;

    private final Integer cod;

    private final String message;

    private final String name;

    private final OpenWeatherMapsCityMainDto main;

    @JsonCreator
    public OpenWeatherMapsCityDto(@JsonProperty("id") final Integer id,
                                  @JsonProperty("cod") final Integer cod,
                                  @JsonProperty("message") final String message,
                                  @JsonProperty("name") final String name,
                                  @JsonProperty("main") final OpenWeatherMapsCityMainDto main) {
        this.id = id;
        this.cod = cod;
        this.message = message;
        this.name = name;
        this.main = main;
    }

    public Integer getId() {
        return id;
    }

    public Integer getCod() {
        return cod;
    }

    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }

    public OpenWeatherMapsCityMainDto getMain() {
        return main;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OpenWeatherMapsCityDto cityDto = (OpenWeatherMapsCityDto) o;
        return Objects.equals(id, cityDto.id) &&
                Objects.equals(cod, cityDto.cod) &&
                Objects.equals(message, cityDto.message) &&
                Objects.equals(name, cityDto.name) &&
                Objects.equals(main, cityDto.main);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cod, message, name, main);
    }
}
