package br.com.cominotti.musical_advisor.infra.adapter.open_weather_maps;

import br.com.cominotti.musical_advisor.infra.dto.open_weather_maps.OpenWeatherMapsCityDto;
import br.com.cominotti.musical_advisor.model.value.FahrenheitValue;
import br.com.cominotti.musical_advisor.model.value.Temperature;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class DefaultOpenWeatherMapsCityDtoToTemperatureAdapter implements OpenWeatherMapsCityDtoToTemperatureAdapter {

    @Override
    public Temperature adapt(@NotNull final OpenWeatherMapsCityDto dto) {
        return new Temperature(
                new FahrenheitValue(
                        dto.getMain().getTemp()
                )
        );
    }
}
