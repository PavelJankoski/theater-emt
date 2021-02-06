package mk.ukim.finki.rating.port;

import mk.ukim.finki.rating.application.service.RatingService;
import mk.ukim.finki.rating.domain.model.RatingId;
import mk.ukim.finki.rating.domain.model.ShowId;
import mk.ukim.finki.rating.domain.repository.RatingRepository;
import mk.ukim.finki.rating.port.dto.request.UserForShowRatingDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/ratings")
public class RatingController {
    private final RatingService ratingService;
    private final RatingRepository ratingRepository;
    public RatingController(RatingService ratingService, RatingRepository ratingRepository) {
        this.ratingService = ratingService;
        this.ratingRepository = ratingRepository;
    }

    @GetMapping("/avg/{showId}")
    public ResponseEntity<List<Object>> getAverageRatingForShow(@PathVariable String showId) {
        return ResponseEntity.ok(this.ratingService.getAverageRatingForShow(showId));
    }

    @PostMapping("/auth/create")
    public ResponseEntity<Float> saveRatingForShow(@RequestBody UserForShowRatingDTO dto) {
        return ResponseEntity.ok(this.ratingService.saveRatingForShow(dto));
    }

    @GetMapping("/auth/exists")
    public ResponseEntity<Boolean> didUserRateShow(@RequestParam String showId, @RequestParam String userId) {
        return ResponseEntity.ok(this.ratingService.didUserRateShow(showId, userId));
    }
}
