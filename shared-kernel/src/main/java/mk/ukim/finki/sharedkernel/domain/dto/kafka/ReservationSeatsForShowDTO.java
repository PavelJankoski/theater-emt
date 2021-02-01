package mk.ukim.finki.sharedkernel.domain.dto.kafka;

import mk.ukim.finki.sharedkernel.domain.financial.Money;

import java.util.List;

public class ReservationSeatsForShowDTO {
    private String showId;
    private List<String> seatIds;
    private Money price;

    public ReservationSeatsForShowDTO(String showId, List<String> seatIds,Money price) {
        this.showId = showId;
        this.seatIds = seatIds;
        this.price=price;
    }

    public ReservationSeatsForShowDTO() {
    }

    public String getShowId() {
        return showId;
    }

    public List<String> getSeatIds() {
        return seatIds;
    }

    public Money getPrice() {
        return price;
    }
}
