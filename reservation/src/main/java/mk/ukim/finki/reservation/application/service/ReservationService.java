package mk.ukim.finki.reservation.application.service;

import mk.ukim.finki.reservation.domain.model.Reservation;
import mk.ukim.finki.sharedkernel.domain.dto.kafka.ReservationSeatsForShowDTO;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.List;

public interface ReservationService {
    List<Reservation> createReservations(@Payload ReservationSeatsForShowDTO dto);

    void deleteReservationsForShow(String id);
}
