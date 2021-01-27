package mk.ukim.finki.theatermanagement.domain.repository;

import mk.ukim.finki.theatermanagement.domain.model.Seat;
import mk.ukim.finki.theatermanagement.domain.model.SeatId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, SeatId> {
}
