package br.com.cominotti.musical_advisor.infra.dto.spotify;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class SpotifyAccessTokenDto {

    private final String accessToken;

    private final String expiresIn;

    private final String tokenType;

    @JsonCreator
    public SpotifyAccessTokenDto(@JsonProperty("access_token") final String accessToken,
                                 @JsonProperty("expires_in") final String expiresIn,
                                 @JsonProperty("token_type") final String tokenType) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.tokenType = tokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public String getTokenType() {
        return tokenType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpotifyAccessTokenDto that = (SpotifyAccessTokenDto) o;
        return Objects.equals(accessToken, that.accessToken) &&
                Objects.equals(expiresIn, that.expiresIn) &&
                Objects.equals(tokenType, that.tokenType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accessToken, expiresIn, tokenType);
    }
}
