package br.com.cominotti.musical_advisor.model.value;

import org.junit.Test;

public class MusicTrackTest {

    @Test(expected = RuntimeException.class)
    public void testInstantiationFailsWhenMusicTitleIsNull() {
        new MusicTrack(null);
    }

    @Test
    public void testInstantiationWithValidArguments() {
        new MusicTrack(
                new MusicTitle("Hi ")
        );
    }
}