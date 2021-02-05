package mk.ukim.finki.reservation.port.dto.request;

import lombok.Getter;
import mk.ukim.finki.sharedkernel.domain.financial.Money;

import java.util.List;

@Getter
public class SelectedSeatsByUserDTO {
    String showId;
    String userId;
    Money ticketPrice;
    List<String> selectedSeats;

}
