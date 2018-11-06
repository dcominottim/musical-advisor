package br.com.cominotti.musical_advisor.infra.adapter;

import br.com.cominotti.musical_advisor.app.query.GetMusicalRecommendationQuery;
import br.com.cominotti.musical_advisor.infra.dto.GetMusicalRecommendationInputDto;
import br.com.cominotti.musical_advisor.infra.dto.MusicalRecommendationDto;

public interface GetMusicalRecommendationAdapter {

    GetMusicalRecommendationQuery.Input adapt(GetMusicalRecommendationInputDto dto);

    MusicalRecommendationDto adapt(GetMusicalRecommendationQuery.Output output);
}
