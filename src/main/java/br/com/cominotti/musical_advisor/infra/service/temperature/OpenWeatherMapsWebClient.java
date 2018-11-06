package br.com.cominotti.musical_advisor.infra.service.temperature;

import br.com.cominotti.musical_advisor.app.Caches;
import br.com.cominotti.musical_advisor.infra.dto.open_weather_maps.OpenWeatherMapsCityDto;
import br.com.cominotti.musical_advisor.model.value.CityName;
import br.com.cominotti.musical_advisor.model.value.GeoCoordinates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.netflix.hystrix.HystrixCommands;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;

@Service
public class OpenWeatherMapsWebClient implements OpenWeatherMapsClient {

    private static Logger LOGGER = LoggerFactory.getLogger(OpenWeatherMapsWebClient.class);

    private final WebClient webClient;

    private final String apiKey;

    @Autowired
    public OpenWeatherMapsWebClient(final WebClient.Builder webClientBuilder,
                                    @Value(BASE_URL_KEY) final String apiBaseUrl,
                                    @Value(API_KEY_KEY) final String apiKey) {
        this(
                webClientBuilder.baseUrl(apiBaseUrl).build(),
                apiKey
        );
    }

    public OpenWeatherMapsWebClient(final WebClient webClient,
                                    final String apiKey) {
        this.webClient = webClient;
        this.apiKey = apiKey;
    }

    @Override
    @Cacheable(Caches.OPEN_WEATHER_MAPS_CITIES_BY_NAME)
    public Mono<OpenWeatherMapsCityDto> getCityTemperature(@NotNull final CityName cityName) {
        return HystrixCommands
                .from(getCityTemperatureImpl(cityName))
                .commandName("getCityTemperature(cityName)")
                .fallback(
                        Mono.empty()
                )
                .toMono()
                .cache();
    }

    private Mono<OpenWeatherMapsCityDto> getCityTemperatureImpl(final CityName cityName) {
        return webClient
                .get()
                .uri(
                        uriBuilder ->
                                uriBuilder
                                        .pathSegment(WEATHER_PATH)
                                        .queryParam(WEATHER_QUERY_PARAM, cityName.getName())
                                        .queryParam(APPID_QUERY_PARAM, apiKey)
                                        .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(OpenWeatherMapsCityDto.class)
                .onErrorMap(
                        mapper -> {
                            LOGGER.error("getCityTemperature(cityName) has failed", mapper);
                            return mapper;
                        }
                )
                .retry(1);
    }

    @Override
    @Cacheable(Caches.OPEN_WEATHER_MAPS_CITIES_BY_GEO_COORDINATES)
    public Mono<OpenWeatherMapsCityDto> getCityTemperature(@NotNull final GeoCoordinates geoCoordinates) {
        return HystrixCommands
                .from(getCityTemperatureImpl(geoCoordinates))
                .commandName("getCityTemperature(geoCoordinates)")
                .fallback(
                        Mono.empty()
                )
                .toMono()
                .cache();
    }

    private Mono<OpenWeatherMapsCityDto> getCityTemperatureImpl(final GeoCoordinates geoCoordinates) {
        return webClient
                .get()
                .uri(
                        uriBuilder ->
                                uriBuilder
                                        .pathSegment(WEATHER_PATH)
                                        .queryParam(LATITUDE_QUERY_PARAM, geoCoordinates.getLatitude())
                                        .queryParam(LONGITUDE_QUERY_PARAM, geoCoordinates.getLongitude())
                                        .queryParam(APPID_QUERY_PARAM, apiKey)
                                        .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(OpenWeatherMapsCityDto.class)
                .onErrorMap(
                        mapper -> {
                            LOGGER.error("getCityTemperature(geoCoordinates) has failed", mapper);
                            return mapper;
                        }
                )
                .retry(1);
    }
}
