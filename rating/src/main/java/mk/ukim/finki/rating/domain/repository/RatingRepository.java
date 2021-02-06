package mk.ukim.finki.rating.domain.repository;

import mk.ukim.finki.rating.domain.model.Rating;
import mk.ukim.finki.rating.domain.model.RatingId;
import mk.ukim.finki.rating.domain.model.ShowId;
import mk.ukim.finki.rating.domain.model.UserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, RatingId> {

    @Query("select AVG(r.rating), count(r.rating) from Rating r where r.showId = ?1")
    List<Object> getAverageRatingForShow(ShowId showId);

    Boolean existsRatingByShowIdAndUserId(ShowId showId, UserId userId);

    List<Rating> findAllByShowId(ShowId showId);
}
