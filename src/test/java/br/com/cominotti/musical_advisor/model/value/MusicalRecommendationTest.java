package br.com.cominotti.musical_advisor.model.value;

import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertNotSame;

public class MusicalRecommendationTest {

    @Test
    public void testGetTracksReturnNewCollection() {
        final MusicalRecommendation musicalRecommendation =
                new MusicalRecommendation(
                        Collections.singletonList(
                                new MusicTrack(
                                        new MusicTitle("Hi")
                                )
                        )
                );

        assertNotSame(
                musicalRecommendation.getTracks(),
                musicalRecommendation.getTracks()
        );
    }
}