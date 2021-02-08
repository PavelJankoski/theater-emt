package mk.ukim.finki.reservation.domain.repository;

import mk.ukim.finki.reservation.domain.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, ReservationId> {
    List<Reservation> findAllByUserId(UserId userId);

    List<Reservation> findAllByShowIdOrderById(ShowId showId);

    Reservation findFirstByShowIdAndSeatId(ShowId showId, SeatId seatId);

    Reservation findFirstBySeatIdAndUserId(SeatId seatId, UserId userId);
}
