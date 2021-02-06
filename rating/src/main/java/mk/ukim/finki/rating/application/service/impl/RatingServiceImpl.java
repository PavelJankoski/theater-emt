package mk.ukim.finki.rating.application.service.impl;

import mk.ukim.finki.rating.application.service.RatingService;
import mk.ukim.finki.rating.domain.model.Rating;
import mk.ukim.finki.rating.domain.repository.RatingRepository;
import mk.ukim.finki.rating.domain.model.ShowId;
import mk.ukim.finki.rating.domain.model.UserId;
import mk.ukim.finki.rating.port.dto.request.UserForShowRatingDTO;
import mk.ukim.finki.sharedkernel.config.KafkaTopics;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;

    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }


    @Override
    public float saveRatingForShow(UserForShowRatingDTO dto) {
        Rating r = new Rating(new UserId(dto.getUserId()), new ShowId(dto.getShowId()), dto.getRating());
        this.ratingRepository.save(r);
        return dto.getRating();
    }

    @Override
    public List<Object> getAverageRatingForShow(String showId) {
        return this.ratingRepository.getAverageRatingForShow(new ShowId(showId));
    }

    @Override
    public Boolean didUserRateShow(String showId, String userId) {
        return this.ratingRepository.existsRatingByShowIdAndUserId(new ShowId(showId), new UserId(userId));
    }


    @KafkaListener(topics = KafkaTopics.DELETE_SHOW,
            groupId = "delete_rating_group",
            containerFactory = "deleteKafkaListenerContainerFactory")
    @Override
    public void deleteRatings(String id) {
        this.ratingRepository.deleteAll(this.ratingRepository.findAllByShowId(new ShowId(id)));
    }


}
