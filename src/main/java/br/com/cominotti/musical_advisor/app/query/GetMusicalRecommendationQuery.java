package br.com.cominotti.musical_advisor.app.query;

import br.com.cominotti.musical_advisor.model.error.BusinessException;
import br.com.cominotti.musical_advisor.model.value.CityName;
import br.com.cominotti.musical_advisor.model.value.GeoCoordinates;
import br.com.cominotti.musical_advisor.model.value.MusicalRecommendation;
import reactor.core.publisher.Mono;

import java.util.Objects;

public interface GetMusicalRecommendationQuery {

    Mono<Output> run(Input input);

    class Input {

        private final CityName cityName;
        private final GeoCoordinates geoCoordinates;

        public Input(final CityName cityName) {
            if (cityName == null) {
                throw new BusinessException("cityName cannot be null");
            }

            this.cityName = cityName;
            this.geoCoordinates = null;
        }

        public Input(final GeoCoordinates geoCoordinates) {
            if (geoCoordinates == null) {
                throw new BusinessException("geoCoordinates cannot be null");
            }

            this.cityName = null;
            this.geoCoordinates = geoCoordinates;
        }

        public CityName getCityName() {
            return cityName;
        }

        public GeoCoordinates getGeoCoordinates() {
            return geoCoordinates;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Input input = (Input) o;
            return Objects.equals(cityName, input.cityName) &&
                    Objects.equals(geoCoordinates, input.geoCoordinates);
        }

        @Override
        public int hashCode() {
            return Objects.hash(cityName, geoCoordinates);
        }
    }

    class Output {

        private final MusicalRecommendation musicalRecommendation;

        Output(final MusicalRecommendation musicalRecommendation) {
            this.musicalRecommendation = musicalRecommendation;
        }

        public MusicalRecommendation getMusicalRecommendation() {
            return musicalRecommendation;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Output output = (Output) o;
            return Objects.equals(musicalRecommendation, output.musicalRecommendation);
        }

        @Override
        public int hashCode() {
            return Objects.hash(musicalRecommendation);
        }
    }
}
