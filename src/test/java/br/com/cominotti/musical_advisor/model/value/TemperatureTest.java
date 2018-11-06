package br.com.cominotti.musical_advisor.model.value;

import org.junit.Test;

import static org.junit.Assert.*;

public class TemperatureTest {

    private static final Temperature temperatureOf19dot9CelsiusDegrees;

    private static final Temperature temperatureOf20CelsiusDegrees;

    private static final Temperature temperatureOf20dot1CelsiusDegrees;

    private static final Temperature temperatureOf67dot9FahrenheitDegrees;

    private static final Temperature temperatureOf68FahrenheitDegrees;

    private static final Temperature temperatureOf68dot1FahrenheitDegrees;

    static {
        temperatureOf19dot9CelsiusDegrees =
                new Temperature(
                        new CelsiusValue(19.0)
                );
        temperatureOf20CelsiusDegrees =
                new Temperature(
                        new CelsiusValue(20.0)
                );
        temperatureOf20dot1CelsiusDegrees =
                new Temperature(
                        new CelsiusValue(20.1)
                );

        temperatureOf67dot9FahrenheitDegrees =
                new Temperature(
                        new FahrenheitValue(67.9)
                );
        temperatureOf68FahrenheitDegrees =
                new Temperature(
                        new FahrenheitValue(68.0)
                );
        temperatureOf68dot1FahrenheitDegrees =
                new Temperature(
                        new FahrenheitValue(68.1)
                );
    }

    @Test(expected = RuntimeException.class)
    public void testInstantiationFailsWhenTemperatureValueIsNull() {
        new Temperature(null);
    }

    @Test
    public void testInstantiationWithValidArguments() {
        new Temperature(
                new TemperatureValue(12.0) {}
        );
    }

    @Test
    public void testIsHigherThanReturnsTrueWhenComparingProperTemperaturesInCelsiusDegrees() {
        assertTrue(
                temperatureOf20CelsiusDegrees.isHigherThan(temperatureOf19dot9CelsiusDegrees)
        );
    }

    @Test
    public void testIsHigherThanReturnsFalseWhenComparingProperTemperaturesInCelsiusDegrees() {
        assertFalse(
                temperatureOf19dot9CelsiusDegrees.isHigherThan(temperatureOf20CelsiusDegrees)
        );
    }

    @Test
    public void testIsHigherThanReturnsFalseWhenComparingEquivalentCelsiusTemperatures() {
        assertFalse(
                temperatureOf20CelsiusDegrees.isHigherThan(temperatureOf20CelsiusDegrees)
        );
    }

    @Test
    public void testIsHigherThanReturnsTrueWhenComparingProperTemperaturesIsFahrenheitDegrees() {
        assertTrue(
                temperatureOf68dot1FahrenheitDegrees.isHigherThan(temperatureOf68FahrenheitDegrees)
        );
    }

    @Test
    public void testIsHigherThanReturnsFalseWhenComparingProperTemperaturesInFahrenheitDegrees() {
        assertFalse(
                temperatureOf68FahrenheitDegrees.isHigherThan(temperatureOf68dot1FahrenheitDegrees)
        );
    }

    @Test
    public void testIsHigherThanReturnsFalseWhenComparingEquivalentFahrenheitTemperatures() {
        assertFalse(
                temperatureOf68FahrenheitDegrees.isHigherThan(temperatureOf68FahrenheitDegrees)
        );
    }

    @Test
    public void testIsLowerThanReturnsTrueWhenComparingProperTemperaturesInCelsiusDegrees() {
        assertTrue(
                temperatureOf19dot9CelsiusDegrees.isLowerThan(temperatureOf20CelsiusDegrees)
        );
    }

    @Test
    public void testIsLowerThanReturnsFalseWhenComparingProperTemperaturesInCelsiusDegrees() {
        assertFalse(
                temperatureOf20CelsiusDegrees.isLowerThan(temperatureOf19dot9CelsiusDegrees)
        );
    }

    @Test
    public void testIsLowerThanReturnsFalseWhenComparingEquivalentCelsiusTemperatures() {
        assertFalse(
                temperatureOf20CelsiusDegrees.isLowerThan(temperatureOf20CelsiusDegrees)
        );
    }

    @Test
    public void testIsLowerThanReturnsTrueWhenComparingProperTemperaturesInFahrenheitDegrees() {
        assertTrue(
                temperatureOf68FahrenheitDegrees.isLowerThan(temperatureOf68dot1FahrenheitDegrees)
        );
    }

    @Test
    public void testIsLowerThanReturnsFalseWhenComparingProperTemperaturesInFahrenheitDegrees() {
        assertFalse(
                temperatureOf68dot1FahrenheitDegrees.isLowerThan(temperatureOf68FahrenheitDegrees)
        );
    }

    @Test
    public void testIsLowerThanReturnsFalseWhenComparingEquivalentFahrenheitTemperatures() {
        assertFalse(
                temperatureOf68FahrenheitDegrees.isLowerThan(temperatureOf68FahrenheitDegrees)
        );
    }

    // comparisons between different scales

    @Test
    public void testIsHigherThanReturnsTrueWhenComparingCelsiusTemperatureAgainstProperFahrenheitTemperature() {
        assertTrue(
                temperatureOf20dot1CelsiusDegrees.isHigherThan(temperatureOf68FahrenheitDegrees)
        );
    }

    @Test
    public void testIsHigherThanReturnsFalseWhenComparingCelsiusTemperatureAgainstProperFahrenheitTemperature() {
        assertFalse(
                temperatureOf19dot9CelsiusDegrees.isHigherThan(temperatureOf68FahrenheitDegrees)
        );
    }

    @Test
    public void testIsHigherThanReturnsFalseWhenComparingEquivalentCelsiusAndFahrenheitTemperatures() {
        assertFalse(
                temperatureOf20CelsiusDegrees.isHigherThan(temperatureOf68FahrenheitDegrees)
        );
    }

    @Test
    public void testIsLowerThanReturnsTrueWhenComparingCelsiusTemperatureAgainstProperFahrenheitTemperature() {
        assertTrue(
                temperatureOf19dot9CelsiusDegrees.isLowerThan(temperatureOf68FahrenheitDegrees)
        );
    }

    @Test
    public void testIsLowerThanReturnsFalseWhenComparingCelsiusTemperatureAgainstProperFahrenheitTemperature() {
        assertFalse(
                temperatureOf68dot1FahrenheitDegrees.isLowerThan(temperatureOf68FahrenheitDegrees)
        );
    }

    @Test
    public void testIsLowerThanReturnsFalseWhenComparingEquivalentCelsiusAndFahrenheitTemperatures() {
        assertFalse(
                temperatureOf20CelsiusDegrees.isLowerThan(temperatureOf68FahrenheitDegrees)
        );
    }

    @Test
    public void testIsEqualToReturnsTrueWhenComparingEquivalentCelsiusTemperatures() {
        assertTrue(
                temperatureOf20CelsiusDegrees.isEqualTo(temperatureOf20CelsiusDegrees)
        );
    }

    @Test
    public void testIsEqualToReturnsTrueWhenComparingEquivalentFahrenheitTemperatures() {
        assertTrue(
                temperatureOf68FahrenheitDegrees.isEqualTo(temperatureOf68FahrenheitDegrees)
        );
    }

    @Test
    public void testIsEqualToReturnsTrueWhenComparingEquivalentCelsiusAndFahrenheitTemperatures() {
        assertTrue(
                temperatureOf20CelsiusDegrees.isEqualTo(temperatureOf68FahrenheitDegrees)
        );
    }

    @Test
    public void testIsEqualToReturnsFalseWhenComparingDifferentCelsiusTemperatures() {
        assertFalse(
                temperatureOf20CelsiusDegrees.isEqualTo(temperatureOf19dot9CelsiusDegrees)
        );
    }

    @Test
    public void testIsEqualToReturnsFalseWhenComparingDifferentFahrenheitTemperatures() {
        assertFalse(
                temperatureOf68FahrenheitDegrees.isEqualTo(temperatureOf67dot9FahrenheitDegrees)
        );
    }

    @Test
    public void testIsEqualToReturnsFalseWhenComparingDifferentCelsiusAndFahrenheitTemperatures() {
        assertFalse(
                temperatureOf20CelsiusDegrees.isEqualTo(temperatureOf68dot1FahrenheitDegrees)
        );
    }

    @Test
    public void testInCelsiusDegreesReturnProperValueForSourceTemperatureInCelsiusDegrees() {
        assertEquals(
                20.0,
                temperatureOf20CelsiusDegrees.inCelsiusDegrees().getValue(),
                0.0
        );
    }

    @Test
    public void testInCelsiusDegreesReturnProperValueForSourceTemperatureInFahrenheitDegrees() {
        assertEquals(
                20.0,
                temperatureOf68FahrenheitDegrees.inCelsiusDegrees().getValue(),
                0.0
        );
    }

    @Test
    public void testInFahrenheitDegreesReturnProperValueForSourceTemperatureInFahrenheitDegrees() {
        assertEquals(
                68,
                temperatureOf68FahrenheitDegrees.inFahrenheitDegrees().getValue(),
                0.0
        );
    }

    @Test
    public void testInFahrenheitDegreesReturnProperValueForSourceTemperatureInCelsiusDegrees() {
        assertEquals(
                68,
                temperatureOf20CelsiusDegrees.inFahrenheitDegrees().getValue(),
                0.0
        );
    }
}