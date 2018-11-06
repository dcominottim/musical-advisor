package br.com.cominotti.musical_advisor.infra.service.temperature;

import br.com.cominotti.musical_advisor.infra.adapter.open_weather_maps.OpenWeatherMapsCityDtoToTemperatureAdapter;
import br.com.cominotti.musical_advisor.infra.dto.open_weather_maps.OpenWeatherMapsCityDto;
import br.com.cominotti.musical_advisor.infra.dto.open_weather_maps.OpenWeatherMapsCityMainDto;
import br.com.cominotti.musical_advisor.model.value.CelsiusValue;
import br.com.cominotti.musical_advisor.model.value.CityName;
import br.com.cominotti.musical_advisor.model.value.GeoCoordinates;
import br.com.cominotti.musical_advisor.model.value.Temperature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OpenWeatherMapsGetCityTemperatureServiceTest {

    private static final OpenWeatherMapsCityDto cityDtoFixture;

    private static final Temperature temperatureOf23CelsiusDegreesFixture;

    private static final CityName campinasCityNameFixture;

    private static final GeoCoordinates geoCoordinatesFixture;

    @InjectMocks
    private OpenWeatherMapsGetCityTemperatureService service;

    @Mock
    private OpenWeatherMapsClient openWeatherMapsClient;

    @Mock
    private OpenWeatherMapsCityDtoToTemperatureAdapter openWeatherMapsCityDtoToTemperatureAdapter;

    static {
        cityDtoFixture =
                new OpenWeatherMapsCityDto(
                        23,
                        200,
                        "",
                        "n",
                        new OpenWeatherMapsCityMainDto(23.2)
                );

        temperatureOf23CelsiusDegreesFixture =
                new Temperature(
                        new CelsiusValue(23.0)
                );

        campinasCityNameFixture =
                new CityName("Campinas");

        geoCoordinatesFixture =
                new GeoCoordinates(
                        20.0,
                        30.0
                );
    }

    @Test
    public void testGetCityTemperatureByNameReturnsTemperature() {
        when(
                openWeatherMapsCityDtoToTemperatureAdapter.adapt(cityDtoFixture)
        )
        .thenReturn(temperatureOf23CelsiusDegreesFixture);

        when(
                openWeatherMapsClient.getCityTemperature(
                        Mockito.any(CityName.class)
                )
        )
        .thenReturn(
               Mono.just(cityDtoFixture)
        );

        StepVerifier.create(
                service.getCityTemperature(campinasCityNameFixture)
        )
        .expectNext(
                temperatureOf23CelsiusDegreesFixture
        )
        .expectComplete()
        .verify();
    }

    @Test
    public void testGetCityTemperatureByNameReturnsEmpty() {
        when(
                openWeatherMapsClient.getCityTemperature(campinasCityNameFixture)
        )
        .thenReturn(
                Mono.empty()
        );

        StepVerifier.create(
                service.getCityTemperature(campinasCityNameFixture)
        )
                .expectNextCount(0)
                .expectComplete()
                .verify();
    }

    @Test
    public void testGetCityTemperatureByGeoCoordinatesReturnsTemperature() {
        when(
                openWeatherMapsCityDtoToTemperatureAdapter.adapt(cityDtoFixture)
        )
        .thenReturn(temperatureOf23CelsiusDegreesFixture);

        when(
                openWeatherMapsClient.getCityTemperature(geoCoordinatesFixture)
        )
        .thenReturn(
                Mono.just(cityDtoFixture)
        );

        StepVerifier.create(
                service.getCityTemperature(geoCoordinatesFixture)
        )
        .expectNext(
                temperatureOf23CelsiusDegreesFixture
        )
        .expectComplete()
        .verify();
    }

    @Test
    public void testGetCityTemperatureByGeoCoordinatesReturnsEmpty() {
        when(
                openWeatherMapsClient.getCityTemperature(
                        Mockito.any(GeoCoordinates.class)
                )
        )
        .thenReturn(Mono.empty());

        StepVerifier.create(
                service.getCityTemperature(geoCoordinatesFixture)
        )
        .expectNextCount(0)
        .expectComplete()
        .verify();
    }
}