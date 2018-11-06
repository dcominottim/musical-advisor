package br.com.cominotti.musical_advisor.model.value;

import org.junit.Test;

public class GeoCoordinatesTest {

    @Test(expected = RuntimeException.class)
    public void testInstantiationFailsWhenLatitudeIsNull() {
        new GeoCoordinates(
                null,
                12.0
        );
    }

    @Test(expected = RuntimeException.class)
    public void testInstantiationFailsWhenLongitudeIsNull() {
        new GeoCoordinates(
                12.0,
                null
        );
    }

    @Test(expected = RuntimeException.class)
    public void testInstantiationFailsWhenAllArgumentsAreNull() {
        new GeoCoordinates(
                null,
                null
        );
    }

    @Test
    public void testInstantiationWithValidArguments() {
        new GeoCoordinates(
                12.0,
                12.0
        );
    }
}