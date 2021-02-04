package mk.ukim.finki.theatermanagement.port;

import mk.ukim.finki.sharedkernel.domain.base.DomainObjectId;
import mk.ukim.finki.theatermanagement.application.service.SceneService;
import mk.ukim.finki.theatermanagement.domain.model.Scene;
import mk.ukim.finki.theatermanagement.domain.model.SceneId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/scenes")
public class SceneController {

    private final SceneService sceneService;

    public SceneController(SceneService sceneService) {
        this.sceneService = sceneService;
    }

    @GetMapping
    public ResponseEntity<List<Scene>> getAllScenes() {
        return ResponseEntity.ok().body(this.sceneService.findAll());
    }

    @GetMapping("/{sceneId}")
    public ResponseEntity<Scene> getSceneById(@PathVariable String sceneId) {
        return ResponseEntity.ok().body(this.sceneService.findById(sceneId));
    }

    @PostMapping
    public ResponseEntity<Scene> createScene(@RequestBody Scene scene){
        return ResponseEntity.ok().body(this.sceneService.createScene(scene));
    }

}
