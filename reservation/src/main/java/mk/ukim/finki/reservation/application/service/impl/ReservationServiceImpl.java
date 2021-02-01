package mk.ukim.finki.reservation.application.service.impl;

import mk.ukim.finki.reservation.application.service.ReservationService;
import mk.ukim.finki.reservation.domain.model.Reservation;
import mk.ukim.finki.reservation.domain.model.SeatId;
import mk.ukim.finki.reservation.domain.model.ShowId;
import mk.ukim.finki.reservation.domain.repository.ReservationRepository;
import mk.ukim.finki.sharedkernel.config.KafkaTopics;
import mk.ukim.finki.sharedkernel.domain.dto.kafka.ReservationSeatsForShowDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository repository;

    public ReservationServiceImpl(ReservationRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = KafkaTopics.RESERVATION_SEATS_FOR_SHOW,
            groupId = "reservation_group",
            containerFactory = "kafkaListenerContainerFactory")
    @Override
    public List<Reservation> createReservations(@Payload ReservationSeatsForShowDTO dto) {
        List<Reservation> reservations = new ArrayList<>();
        for(String seatId : dto.getSeatIds()) {
            Reservation reservation = new Reservation(null, new ShowId(dto.getShowId()), new SeatId(seatId),dto.getPrice());
            reservations.add(reservation);
        }
        return repository.saveAll(reservations);
    }
}
