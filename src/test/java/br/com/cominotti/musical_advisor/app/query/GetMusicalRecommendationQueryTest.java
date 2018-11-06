package br.com.cominotti.musical_advisor.app.query;

import br.com.cominotti.musical_advisor.model.error.BusinessException;
import br.com.cominotti.musical_advisor.model.value.CityName;
import br.com.cominotti.musical_advisor.model.value.GeoCoordinates;
import org.junit.Test;

public class GetMusicalRecommendationQueryTest {

    @Test(expected = BusinessException.class)
    public void testInstantiationFailsWhenCityNameIsNull() {
        new GetMusicalRecommendationQuery.Input((CityName) null);
    }

    @Test(expected = BusinessException.class)
    public void testInstantiationFailsWhenGeoCoordinatesIsNull() {
        new GetMusicalRecommendationQuery.Input((GeoCoordinates) null);
    }


}