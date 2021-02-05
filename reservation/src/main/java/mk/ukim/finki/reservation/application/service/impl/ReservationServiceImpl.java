package mk.ukim.finki.reservation.application.service.impl;

import mk.ukim.finki.reservation.application.service.ReservationService;
import mk.ukim.finki.reservation.domain.model.*;
import mk.ukim.finki.reservation.domain.repository.ReservationRepository;
import mk.ukim.finki.reservation.port.dto.request.SelectedSeatsByUserDTO;
import mk.ukim.finki.sharedkernel.config.KafkaTopics;
import mk.ukim.finki.sharedkernel.domain.dto.kafka.ReservationSeatsForShowDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
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

    @Transactional
    @KafkaListener(topics = KafkaTopics.RESERVATION_EDIT_SHOW,
            groupId = "reservation_group",
            containerFactory = "kafkaListenerContainerFactory")
    @Override
    public List<Reservation> editShowReservations(ReservationSeatsForShowDTO dto) {
        this.repository.deleteAll(this.repository.findAllByShowIdOrderById(new ShowId(dto.getShowId())));
        List<Reservation> reservations = new ArrayList<>();
        for(String seatId : dto.getSeatIds()) {
            Reservation reservation = new Reservation(null, new ShowId(dto.getShowId()), new SeatId(seatId),dto.getPrice());
            reservations.add(reservation);
        }
        return repository.saveAll(reservations);
    }

    @KafkaListener(topics = KafkaTopics.DELETE_SHOW,
            groupId = "delete_show_group",
            containerFactory = "deleteShowKafkaListenerContainerFactory")
    @Override
    public void deleteReservationsForShow(String id) {
        this.repository.deleteAll(this.repository.findAllByShowIdOrderById(new ShowId(id)));
    }

    @Override
    public List<Reservation> findAllReservationsForShow(ShowId id) {
        return this.repository.findAllByShowIdOrderById(id);
    }

    @Override
    public List<Reservation> makeReservation(SelectedSeatsByUserDTO dto) {
        List<Reservation> reservations = new ArrayList<>();
        for(String seat : dto.getSelectedSeats()) {
            String [] parts = seat.split(";");
            Reservation r = this.repository.findFirstByShowIdAndSeatId(new ShowId(dto.getShowId()), new SeatId(parts[0]));
            r.setReservatedOn(Instant.now());
            r.setSeatRow(Integer.parseInt(parts[1]));
            r.setSeatNo(Integer.parseInt(parts[2]));
            r.setUserId(new UserId(dto.getUserId()));
            r.setStatus(ReservationStatus.RESERVED);
            r.setPrice(dto.getTicketPrice());
            reservations.add(r);
        }
        return this.repository.saveAll(reservations);
    }


}
