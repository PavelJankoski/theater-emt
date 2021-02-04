package mk.ukim.finki.theatermanagement.port;

import mk.ukim.finki.theatermanagement.application.service.ActorService;
import mk.ukim.finki.theatermanagement.domain.model.Actor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/actors")
public class ActorController {
    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping
    public ResponseEntity<List<Actor>> getAllActors() {
        return ResponseEntity.ok().body(this.actorService.findAll());
    }

    @GetMapping("/{actorId}")
    public ResponseEntity<Actor> getActorById(@PathVariable String actorId) {
        return ResponseEntity.ok().body(this.actorService.findActorById(actorId));
    }

    @PutMapping("/{actorId}/delete")
    public HttpStatus deleteActor(@PathVariable String actorId) {
        this.actorService.deleteActorById(actorId);
        return HttpStatus.OK;
    }

    @PostMapping
    public ResponseEntity<Actor> createActor(@RequestBody Actor actor) {
        return ResponseEntity.ok().body(this.actorService.save(actor));
    }

}
