package br.com.cominotti.musical_advisor.model.value;

public enum MusicalGenre {

    PARTY("party"),
    POP("pop"),
    ROCK("rock"),
    CLASSICAL("classical");

    private final String spotifyGenreName;

    MusicalGenre(final String spotifyGenreName) {
        this.spotifyGenreName = spotifyGenreName;
    }

    public String getSpotifyGenreName() {
        return spotifyGenreName;
    }
}
