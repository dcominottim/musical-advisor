package br.com.cominotti.musical_advisor.model.service.rule;

import br.com.cominotti.musical_advisor.model.error.BusinessException;
import br.com.cominotti.musical_advisor.model.service.MusicalAdvisorRuleAccumulator;
import br.com.cominotti.musical_advisor.model.value.CelsiusValue;
import br.com.cominotti.musical_advisor.model.value.MusicalGenre;
import br.com.cominotti.musical_advisor.model.value.Temperature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MusicalAdvisorAbstractRuleTest {

    private final Temperature temperatureOf14CelsiusDegreesFixture;

    private final Temperature temperatureOf14dot2CelsiusDegreesFixture;

    @Mock
    private MusicalAdvisorRuleAccumulator accumulator;

    {
        temperatureOf14CelsiusDegreesFixture =
                new Temperature(
                        new CelsiusValue(14.0)
                );

        temperatureOf14dot2CelsiusDegreesFixture =
                new Temperature(
                        new CelsiusValue(14.2)
                );
    }

    @Test(expected = RuntimeException.class)
    public void testInstantiationFailsWithNullTemperatureLowerBound() {
        new MusicalAdvisorAbstractRule(
                null,
                temperatureOf14CelsiusDegreesFixture,
                MusicalGenre.CLASSICAL
        ) {};
    }

    @Test(expected = RuntimeException.class)
    public void testInstantiationFailsWithNullTemperatureHigherBound() {
        new MusicalAdvisorAbstractRule(
                temperatureOf14CelsiusDegreesFixture,
                null,
                MusicalGenre.CLASSICAL
        ) {};
    }

    @Test(expected = RuntimeException.class)
    public void testInstantiationFailsWithNullMusicalGenreToIncludeIfTemperatureIsWithinBounds() {
        new MusicalAdvisorAbstractRule(
                temperatureOf14CelsiusDegreesFixture,
                temperatureOf14dot2CelsiusDegreesFixture,
                null
        ) {};
    }

    @Test(expected = BusinessException.class)
    public void testInstantiationFailsWithWrongLowerAndUpperBoundTemperatures() {
        new MusicalAdvisorAbstractRule(
                temperatureOf14dot2CelsiusDegreesFixture,
                temperatureOf14CelsiusDegreesFixture,
                MusicalGenre.CLASSICAL
        ) {};
    }

    @Test
    public void testInstantiationWithValidArguments() {
        new MusicalAdvisorAbstractRule(
                temperatureOf14CelsiusDegreesFixture,
                temperatureOf14dot2CelsiusDegreesFixture,
                MusicalGenre.CLASSICAL
        ) {};
    }

    @Test
    public void testApplyDoesNotInvokeAccumulatorWhenTemperatureIsBelowLowerBound() {
        new MusicalAdvisorAbstractRule(
                temperatureOf14CelsiusDegreesFixture,
                temperatureOf14dot2CelsiusDegreesFixture,
                MusicalGenre.CLASSICAL
        ) {
        }.apply(
                new Temperature(
                        new CelsiusValue(13.0)
                ),
                accumulator
        );

        verify(
                accumulator,
                times(0)
        ).apply(any());
    }

    @Test
    public void testApplyDoesNotInvokeAccumulatorWhenTemperatureIsAboveUpperBound() {
        new MusicalAdvisorAbstractRule(
                temperatureOf14CelsiusDegreesFixture,
                temperatureOf14dot2CelsiusDegreesFixture,
                MusicalGenre.CLASSICAL
        ) {
        }.apply(
                new Temperature(
                        new CelsiusValue(14.3)
                ),
                accumulator
        );

        verify(
                accumulator,
                times(0)
        ).apply(any());
    }

    @Test
    public void testApplyInvokesAccumulatorWhenTemperatureIsWithinBounds() {
        new MusicalAdvisorAbstractRule(
                temperatureOf14CelsiusDegreesFixture,
                temperatureOf14dot2CelsiusDegreesFixture,
                MusicalGenre.CLASSICAL
        ) {
        }.apply(
                new Temperature(
                        new CelsiusValue(14.1)
                ),
                accumulator
        );

        verify(
                accumulator,
                times(1)
        ).apply(
                eq(MusicalGenre.CLASSICAL)
        );
    }

    @Test
    public void testApplyInvokesAccumulatorWhenTemperatureIsEqualToLowerBound() {
        new MusicalAdvisorAbstractRule(
                temperatureOf14CelsiusDegreesFixture,
                temperatureOf14dot2CelsiusDegreesFixture,
                MusicalGenre.CLASSICAL
        ) {
        }.apply(
                temperatureOf14CelsiusDegreesFixture,
                accumulator
        );

        verify(
                accumulator,
                times(1)
        ).apply(
                eq(MusicalGenre.CLASSICAL)
        );
    }

    @Test
    public void testApplyInvokesAccumulatorWhenTemperatureIsEqualToUpperBound() {
        new MusicalAdvisorAbstractRule(
                temperatureOf14CelsiusDegreesFixture,
                temperatureOf14dot2CelsiusDegreesFixture,
                MusicalGenre.CLASSICAL
        ) {
        }.apply(
                temperatureOf14dot2CelsiusDegreesFixture,
                accumulator
        );

        verify(
                accumulator,
                times(1)
        ).apply(
                eq(MusicalGenre.CLASSICAL)
        );
    }
}