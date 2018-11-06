package br.com.cominotti.musical_advisor.infra.service.temperature;

import br.com.cominotti.musical_advisor.infra.dto.open_weather_maps.OpenWeatherMapsCityDto;
import br.com.cominotti.musical_advisor.model.value.CityName;
import br.com.cominotti.musical_advisor.model.value.GeoCoordinates;
import reactor.core.publisher.Mono;

public interface OpenWeatherMapsClient {

    String BASE_URL_KEY = "${openweathermaps.api.base.url}";

    String API_KEY_KEY = "${openweathermaps.api.key}";

    String WEATHER_PATH = "weather";

    String WEATHER_QUERY_PARAM = "q";

    String APPID_QUERY_PARAM = "appid";

    String LATITUDE_QUERY_PARAM = "lat";

    String LONGITUDE_QUERY_PARAM = "lon";

    Mono<OpenWeatherMapsCityDto> getCityTemperature(CityName cityName);

    Mono<OpenWeatherMapsCityDto> getCityTemperature(GeoCoordinates geoCoordinates);
}
