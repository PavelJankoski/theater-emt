package mk.ukim.finki.rating.port.dto.request;

import lombok.Getter;

@Getter
public class UserForShowRatingDTO {
    private String userId;
    private String showId;
    private float rating;
}
