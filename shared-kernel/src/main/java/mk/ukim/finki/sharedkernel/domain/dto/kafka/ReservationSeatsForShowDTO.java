package mk.ukim.finki.sharedkernel.domain.dto.kafka;

import java.util.List;

public class ReservationSeatsForShowDTO {
    private String showId;
    private List<String> seatIds;

    public ReservationSeatsForShowDTO(String showId, List<String> seatIds) {
        this.showId = showId;
        this.seatIds = seatIds;
    }

    public ReservationSeatsForShowDTO() {
    }

    public String getShowId() {
        return showId;
    }

    public List<String> getSeatIds() {
        return seatIds;
    }
}
