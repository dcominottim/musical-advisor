package br.com.cominotti.musical_advisor.app.service;

import br.com.cominotti.musical_advisor.model.value.CityName;
import br.com.cominotti.musical_advisor.model.value.GeoCoordinates;
import br.com.cominotti.musical_advisor.model.value.Temperature;
import reactor.core.publisher.Mono;

public interface GetCityTemperatureService {

    Mono<Temperature> getCityTemperature(CityName cityName);

    Mono<Temperature> getCityTemperature(GeoCoordinates cityGeoCoordinates);
}
