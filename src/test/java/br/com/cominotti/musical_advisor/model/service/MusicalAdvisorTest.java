package br.com.cominotti.musical_advisor.model.service;

import br.com.cominotti.musical_advisor.model.service.rule.MusicalAdvisorRule;
import br.com.cominotti.musical_advisor.model.value.CelsiusValue;
import br.com.cominotti.musical_advisor.model.value.MusicalGenre;
import br.com.cominotti.musical_advisor.model.value.Temperature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MusicalAdvisorTest {

    private final Temperature temperatureOf14CelsiusDegreesFixture;

    @Mock
    private MusicalAdvisorRule musicalAdvisorRule1;

    @Mock
    private MusicalAdvisorRule musicalAdvisorRule2;

    {
        temperatureOf14CelsiusDegreesFixture =
                new Temperature(
                        new CelsiusValue(14.0)
                );
    }

    @Test
    public void testEvaluateInvokesApplyOnAllRules() {
        final MusicalAdvisor musicalAdvisor =
                new MusicalAdvisor(
                        Arrays.asList(
                                musicalAdvisorRule1,
                                musicalAdvisorRule2
                        )
                );

        musicalAdvisor.evaluate(temperatureOf14CelsiusDegreesFixture);

        verify(
                musicalAdvisorRule1,
                times(
                        1
                )
        ).apply(
                eq(temperatureOf14CelsiusDegreesFixture),
                any()
        );

        verify(
                musicalAdvisorRule2,
                times(
                        1
                )
        ).apply(
                eq(temperatureOf14CelsiusDegreesFixture),
                any()
        );
    }

    @Test
    public void testEvaluateReturnsClassicalMusicGenre() {
        final MusicalAdvisor musicalAdvisor =
                new MusicalAdvisor(
                        Arrays.asList(
                                musicalAdvisorRule1,
                                musicalAdvisorRule2
                        )
                );

        doAnswer(
                invocation -> {
                    final MusicalAdvisorRuleAccumulator accumulator =
                            invocation.getArgument(1);

                    accumulator.apply(MusicalGenre.CLASSICAL);
                    return null;
                }
        ).when(
                musicalAdvisorRule1
        ).apply(
                eq(temperatureOf14CelsiusDegreesFixture),
                any()
        );

        final Set<MusicalGenre> musicalGenres =
                musicalAdvisor.evaluate(temperatureOf14CelsiusDegreesFixture);

        assertEquals(1, musicalGenres.size());
        assertTrue(
                musicalGenres.contains(MusicalGenre.CLASSICAL)
        );
    }
}