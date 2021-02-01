package mk.ukim.finki.reservation.domain.repository;

import mk.ukim.finki.reservation.domain.model.Reservation;
import mk.ukim.finki.reservation.domain.model.ReservationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, ReservationId> {

}
