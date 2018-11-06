package br.com.cominotti.musical_advisor.model.value;

import br.com.cominotti.musical_advisor.model.error.BusinessException;
import org.junit.Test;

public class MusicTitleTest {
    @Test(expected = RuntimeException.class)
    public void testInstantiationFailsWhenNameIsNull() {
        new MusicTitle(null);
    }

    @Test(expected = BusinessException.class)
    public void testInstantiationFailsWhenNameIsEmpty() {
        new MusicTitle("");
    }

    @Test(expected = BusinessException.class)
    public void testInstantiationFailsWhenNameIsBlank() {
        new MusicTitle(" ");
    }

    @Test
    public void testInstantiationWithValidArguments() {
        new MusicTitle("Lucky 13");
    }
}