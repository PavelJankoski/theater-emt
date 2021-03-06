package mk.ukim.finki.reservation.port;


import mk.ukim.finki.reservation.application.service.ReservationService;
import mk.ukim.finki.reservation.domain.model.Reservation;
import mk.ukim.finki.reservation.domain.model.ShowId;
import mk.ukim.finki.reservation.port.dto.request.SelectedSeatsByUserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/all/{showId}")
    public ResponseEntity<List<Reservation>> getReservationsForShow(@PathVariable String showId) {
        return ResponseEntity.ok(reservationService.findAllReservationsForShow(new ShowId(showId)));
    }

    @GetMapping("/auth/by-user/{userId}")
    public ResponseEntity<List<Reservation>> getAllReservationsFromUser(@PathVariable String userId) {
        return ResponseEntity.ok(this.reservationService.findAllReservationsForUser(userId));
    }

    @PutMapping("/auth/make-reservation")
    public ResponseEntity<List<Reservation>> makeReservation(@RequestBody SelectedSeatsByUserDTO dto) {
        return ResponseEntity.ok(this.reservationService.makeReservation(dto));
    }

    @PutMapping("/auth/cancel-reservation/{reservationId}")
    public ResponseEntity<Reservation> cancelReservation(@PathVariable String reservationId) {
        return ResponseEntity.ok(this.reservationService.removeUserFromReservation(reservationId));
    }
}
