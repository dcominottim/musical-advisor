package br.com.cominotti.musical_advisor.infra.adapter;

import br.com.cominotti.musical_advisor.app.query.GetMusicalRecommendationQuery;
import br.com.cominotti.musical_advisor.infra.dto.GetMusicalRecommendationInputDto;
import br.com.cominotti.musical_advisor.infra.dto.MusicTrackDto;
import br.com.cominotti.musical_advisor.infra.dto.MusicalRecommendationDto;
import br.com.cominotti.musical_advisor.model.value.CityName;
import br.com.cominotti.musical_advisor.model.value.GeoCoordinates;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.stream.Collectors;

@Service
public class DefaultGetMusicalRecommendationAdapter implements GetMusicalRecommendationAdapter {

    @Override
    public GetMusicalRecommendationQuery.Input adapt(@NotNull final GetMusicalRecommendationInputDto dto) {
        return (dto.getLatitude() != null && dto.getLongitude() != null)
                ? new GetMusicalRecommendationQuery.Input(
                        new GeoCoordinates(
                                dto.getLatitude(),
                                dto.getLongitude()
                        )
                )
                : new GetMusicalRecommendationQuery.Input(
                        new CityName(dto.getCityName())
                );
    }

    @Override
    public MusicalRecommendationDto adapt(@NotNull final GetMusicalRecommendationQuery.Output output) {
        return new MusicalRecommendationDto(
                output.getMusicalRecommendation().getTracks()
                        .stream()
                        .map(
                                musicTrack -> new MusicTrackDto(musicTrack.getTitle().getTitle())
                        )
                        .collect(Collectors.toSet())
        );
    }
}
