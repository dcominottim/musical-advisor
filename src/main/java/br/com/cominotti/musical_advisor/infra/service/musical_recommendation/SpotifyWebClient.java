package br.com.cominotti.musical_advisor.infra.service.musical_recommendation;

import br.com.cominotti.musical_advisor.app.Caches;
import br.com.cominotti.musical_advisor.infra.dto.spotify.SpotifyAccessTokenDto;
import br.com.cominotti.musical_advisor.infra.dto.spotify.SpotifyRecommendationDto;
import br.com.cominotti.musical_advisor.model.value.MusicalGenre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.netflix.hystrix.HystrixCommands;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;

@Service
public class SpotifyWebClient implements SpotifyClient {

    private static Logger LOGGER = LoggerFactory.getLogger(SpotifyWebClient.class);

    private final WebClient apiWebClient;

    private final WebClient authenticationWebClient;

    private final String spotifyCliendId;

    private final String spotifyClientSecret;

    @Autowired
    public SpotifyWebClient(final WebClient.Builder webClientBuilder,
                            @Value(BASE_URL_KEY) final String apiBaseUrl,
                            @Value(ACCESS_TOKEN_URL_KEY) final String accessTokenUrl,
                            @Value(CLIENT_ID_KEY) final String spotifyCliendId,
                            @Value(CLIENT_SECRET_KEY) final String spotifyClientSecret) {
        this.apiWebClient = webClientBuilder.baseUrl(apiBaseUrl).build();
        this.authenticationWebClient = webClientBuilder.baseUrl(accessTokenUrl).build();
        this.spotifyCliendId = spotifyCliendId;
        this.spotifyClientSecret = spotifyClientSecret;
    }

    public SpotifyWebClient(final WebClient apiWebClient,
                            final WebClient authenticationWebClient,
                            final String spotifyCliendId,
                            final String spotifyClientSecret) {
        this.apiWebClient = apiWebClient;
        this.authenticationWebClient = authenticationWebClient;
        this.spotifyCliendId = spotifyCliendId;
        this.spotifyClientSecret = spotifyClientSecret;
    }

    @Override
    @Cacheable(Caches.SPOTIFY_RECOMMENDATIONS)
    public Mono<SpotifyRecommendationDto> getRecommendation(@NotNull final MusicalGenre musicalGenre) {
        return HystrixCommands
                .from(getRecommendationImpl(musicalGenre))
                .commandName("getRecommendation(musicalGenre)")
                .fallback(
                        Mono.empty()
                )
                .toMono()
                .cache();
    }

    private Mono<SpotifyRecommendationDto> getRecommendationImpl(final MusicalGenre musicalGenre) {
        return requestAccessToken()
                .flatMap(
                        spotifyAccessTokenDto ->{
                            final String accessToken = spotifyAccessTokenDto.getAccessToken();

                            return apiWebClient
                                    .get()
                                    .uri(
                                            uriBuilder ->
                                                    uriBuilder
                                                            .pathSegment(RECOMMENDATIONS_URL)
                                                            .queryParam(GENRES_QUERY_PARAM, musicalGenre.getSpotifyGenreName())
                                                            .build()
                                    )
                                    .header(
                                            "Authorization",
                                            "Bearer " + accessToken
                                    )
                                    .accept(MediaType.APPLICATION_JSON)
                                    .retrieve()
                                    .bodyToMono(SpotifyRecommendationDto.class)
                                    .onErrorMap(
                                            mapper -> {
                                                LOGGER.error("getRecommendation(musicalGenre) has failed", mapper);
                                                return mapper;
                                            }
                                    )
                                    .retry(1);
                        }
                );
    }

    private Mono<SpotifyAccessTokenDto> requestAccessToken() {
        return HystrixCommands
                .from(requestAccessTokenImpl())
                .commandName("requestAccessToken")
                .fallback(
                        Mono.empty()
                )
                .toMono();
    }

    private Mono<SpotifyAccessTokenDto> requestAccessTokenImpl() {
        return authenticationWebClient
                .post()
                .header(
                        "Authorization",
                        "Basic " +
                                Base64Utils.encodeToString(
                                        (spotifyCliendId + ":" + spotifyClientSecret).getBytes()
                                )
                )
                .header(
                        HttpHeaders.CONTENT_TYPE,
                        MediaType.APPLICATION_FORM_URLENCODED_VALUE
                )
                .header(
                        HttpHeaders.CONTENT_LENGTH,
                        ACCESS_TOKEN_REQUEST_BODY.length() + ""

                )
                .body(
                        BodyInserters.fromObject(ACCESS_TOKEN_REQUEST_BODY)
                )
                .retrieve()
                .bodyToMono(SpotifyAccessTokenDto.class)
                .onErrorMap(
                        mapper -> {
                            LOGGER.error("requestAccessToken has failed", mapper);
                            return mapper;
                        }
                )
                .retry(1);
    }
}
