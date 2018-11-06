package br.com.cominotti.musical_advisor.infra.service.musical_recommendation;

import br.com.cominotti.musical_advisor.infra.dto.spotify.SpotifyRecommendationDto;
import br.com.cominotti.musical_advisor.model.value.MusicalGenre;
import reactor.core.publisher.Mono;

public interface SpotifyClient {

    String BASE_URL_KEY = "${spotify.api.base.url}";

    String ACCESS_TOKEN_URL_KEY = "${spotify.api.access.token.url}";

    String CLIENT_ID_KEY = "${spotify.api.client.id}";

    String CLIENT_SECRET_KEY = "${spotify.api.client.secret}";

    String GENRES_QUERY_PARAM = "seed_genres";

    String RECOMMENDATIONS_URL = "recommendations";

    String ACCESS_TOKEN_REQUEST_BODY = "grant_type=client_credentials";

    Mono<SpotifyRecommendationDto> getRecommendation(MusicalGenre musicalGenre);
}
