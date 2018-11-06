package br.com.cominotti.musical_advisor.api;

import br.com.cominotti.musical_advisor.app.query.GetMusicalRecommendationQuery;
import br.com.cominotti.musical_advisor.infra.adapter.GetMusicalRecommendationAdapter;
import br.com.cominotti.musical_advisor.infra.dto.GetMusicalRecommendationInputDto;
import br.com.cominotti.musical_advisor.infra.dto.MusicalRecommendationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/advisor")
public class AdvisorEndpoint {

    private final GetMusicalRecommendationAdapter getMusicalRecommendationAdapter;

    private final GetMusicalRecommendationQuery getMusicalRecommendationQuery;

    @Autowired
    public AdvisorEndpoint(final GetMusicalRecommendationAdapter getMusicalRecommendationAdapter,
                           final GetMusicalRecommendationQuery getMusicalRecommendationQuery) {
        this.getMusicalRecommendationAdapter = getMusicalRecommendationAdapter;
        this.getMusicalRecommendationQuery = getMusicalRecommendationQuery;
    }

    @GetMapping
    private Mono<ResponseEntity<MusicalRecommendationDto>> getMusicalSuggestion(@Valid final GetMusicalRecommendationInputDto input) {
        return getMusicalRecommendationQuery.run(
                getMusicalRecommendationAdapter.adapt(input)
        ).map(
                output ->
                        new ResponseEntity<>(
                                getMusicalRecommendationAdapter.adapt(output),
                                HttpStatus.OK
                        )
        );
    }
}
