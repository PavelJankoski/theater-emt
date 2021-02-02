package mk.ukim.finki.reservation.domain.repository;

import mk.ukim.finki.reservation.domain.model.Reservation;
import mk.ukim.finki.reservation.domain.model.ReservationId;
import mk.ukim.finki.reservation.domain.model.ShowId;
import mk.ukim.finki.reservation.domain.model.UserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, ReservationId> {
    List<Reservation> findAllByShowIdAndUserId(ShowId showId, UserId userId);

    List<Reservation> findAllByShowId(ShowId showId);
}
