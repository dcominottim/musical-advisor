package br.com.cominotti.musical_advisor.model.value;

import br.com.cominotti.musical_advisor.model.error.BusinessException;
import org.junit.Test;

public class CityNameTest {

    @Test(expected = RuntimeException.class)
    public void testInstantiationFailsWhenNameIsNull() {
        new CityName(null);
    }

    @Test(expected = BusinessException.class)
    public void testInstantiationFailsWhenNameIsEmpty() {
        new CityName("");
    }

    @Test(expected = BusinessException.class)
    public void testInstantiationFailsWhenNameIsBlank() {
        new CityName(" ");
    }

    @Test(expected = BusinessException.class)
    public void testInstantiationFailsWhenNameHasDigits() {
        new CityName("13");
    }

    @Test
    public void testInstantiationWithValidArguments() {
        new CityName("São Paulo");
    }
}