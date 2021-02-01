package mk.ukim.finki.theatermanagement.port;

import mk.ukim.finki.theatermanagement.application.service.ShowService;
import mk.ukim.finki.theatermanagement.domain.model.Scene;
import mk.ukim.finki.theatermanagement.domain.model.SceneId;
import mk.ukim.finki.theatermanagement.domain.model.Show;
import mk.ukim.finki.theatermanagement.domain.repository.SceneRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/shows")
public class ShowController {
    private final ShowService showService;
    private final SceneRepository sceneRepository;
    public ShowController(ShowService showService, SceneRepository sceneRepository) {
        this.showService = showService;
        this.sceneRepository = sceneRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<Show> createShow(@RequestBody Show show) {
        Scene s = sceneRepository.findById(new SceneId("70ea695b-0bcf-48c2-9390-dcda278e0854")).orElseThrow(RuntimeException::new);
        show.setScene(s);
        return ResponseEntity.ok().body(this.showService.createShow(show));
    }

}
