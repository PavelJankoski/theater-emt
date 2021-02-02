package mk.ukim.finki.theatermanagement.port;

import mk.ukim.finki.sharedkernel.domain.base.DomainObjectId;
import mk.ukim.finki.theatermanagement.application.service.SceneService;
import mk.ukim.finki.theatermanagement.domain.model.Scene;
import mk.ukim.finki.theatermanagement.domain.model.SceneId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/scene")
public class SceneController {

    private final SceneService sceneService;

    public SceneController(SceneService sceneService) {
        this.sceneService = sceneService;
    }

    @PostMapping("/admin/create")
    public ResponseEntity<Scene> createScene(@RequestBody Scene scene){
        return ResponseEntity.ok().body(this.sceneService.createScene(scene));
    }

}
