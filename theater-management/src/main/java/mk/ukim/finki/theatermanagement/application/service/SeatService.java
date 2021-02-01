package mk.ukim.finki.theatermanagement.application.service;

import mk.ukim.finki.theatermanagement.domain.model.Scene;
import mk.ukim.finki.theatermanagement.domain.model.Seat;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.List;

public interface SeatService {

    List<Seat> createSeatsForScene(@Payload Scene scene);
}
