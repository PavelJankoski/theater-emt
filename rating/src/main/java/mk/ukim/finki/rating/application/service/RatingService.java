package mk.ukim.finki.rating.application.service;

import mk.ukim.finki.rating.domain.model.Rating;
import mk.ukim.finki.rating.domain.model.ShowId;
import mk.ukim.finki.rating.port.dto.request.UserForShowRatingDTO;

import java.util.List;

public interface RatingService {
    float saveRatingForShow(UserForShowRatingDTO dto);

    List<Object> getAverageRatingForShow(String showId);

    Boolean didUserRateShow(String showId, String userId);

    void deleteRatings(String showId);
}
