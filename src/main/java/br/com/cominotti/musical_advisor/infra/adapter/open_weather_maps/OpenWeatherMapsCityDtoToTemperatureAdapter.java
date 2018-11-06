package br.com.cominotti.musical_advisor.infra.adapter.open_weather_maps;

import br.com.cominotti.musical_advisor.infra.dto.open_weather_maps.OpenWeatherMapsCityDto;
import br.com.cominotti.musical_advisor.model.value.Temperature;

public interface OpenWeatherMapsCityDtoToTemperatureAdapter {

    Temperature adapt(OpenWeatherMapsCityDto dto);
}
