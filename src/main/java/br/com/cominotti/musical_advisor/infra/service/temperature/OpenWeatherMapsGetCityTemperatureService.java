package br.com.cominotti.musical_advisor.infra.service.temperature;

import br.com.cominotti.musical_advisor.app.service.GetCityTemperatureService;
import br.com.cominotti.musical_advisor.infra.adapter.open_weather_maps.OpenWeatherMapsCityDtoToTemperatureAdapter;
import br.com.cominotti.musical_advisor.model.value.CityName;
import br.com.cominotti.musical_advisor.model.value.GeoCoordinates;
import br.com.cominotti.musical_advisor.model.value.Temperature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;

@Service
public class OpenWeatherMapsGetCityTemperatureService implements GetCityTemperatureService {

    private final OpenWeatherMapsClient openWeatherMapsClient;

    private final OpenWeatherMapsCityDtoToTemperatureAdapter openWeatherMapsCityDtoToTemperatureAdapter;

    @Autowired
    public OpenWeatherMapsGetCityTemperatureService(final OpenWeatherMapsClient openWeatherMapsClient,
                                                    final OpenWeatherMapsCityDtoToTemperatureAdapter openWeatherMapsCityDtoToTemperatureAdapter) {
        this.openWeatherMapsClient = openWeatherMapsClient;
        this.openWeatherMapsCityDtoToTemperatureAdapter = openWeatherMapsCityDtoToTemperatureAdapter;
    }

    @Override
    public Mono<Temperature> getCityTemperature(@NotNull final CityName cityName) {
        return openWeatherMapsClient.getCityTemperature(cityName)
                .flatMap(
                        cityDto ->
                                cityDto.getCod() == HttpStatus.OK.value()
                                        ? Mono.just(
                                                openWeatherMapsCityDtoToTemperatureAdapter.adapt(cityDto)
                                        )
                                        :  Mono.empty()
                );
    }

    @Override
    public Mono<Temperature> getCityTemperature(@NotNull final GeoCoordinates cityGeoCoordinates) {
        return openWeatherMapsClient.getCityTemperature(cityGeoCoordinates)
                .flatMap(
                        cityDto ->
                                cityDto.getCod() == HttpStatus.OK.value()
                                        ? Mono.just(
                                                openWeatherMapsCityDtoToTemperatureAdapter.adapt(cityDto)
                                        )
                                        : Mono.empty()
                );
    }
}
