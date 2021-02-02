package mk.ukim.finki.theatermanagement.port;

import mk.ukim.finki.theatermanagement.application.service.ShowService;
import mk.ukim.finki.theatermanagement.domain.model.Scene;
import mk.ukim.finki.theatermanagement.domain.model.SceneId;
import mk.ukim.finki.theatermanagement.domain.model.Show;
import mk.ukim.finki.theatermanagement.domain.repository.SceneRepository;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/all")
    public ResponseEntity<List<Show>> getAllShows(@RequestParam(required = false) String term){
        if(term!=null) {
            return ResponseEntity.ok().body(this.showService.searchShow(term));
        }
        else{
            return ResponseEntity.ok().body(this.showService.findAll());
        }

    }

    @GetMapping("/all/paged")
    public Page<Show> getAllShowsPaged(@RequestHeader(name = "page", defaultValue = "0", required = false) Integer pageNo,
                                       @RequestHeader(name = "page-size", defaultValue = "6", required = false) Integer pageSize,
                                       @RequestHeader(name = "sort", defaultValue = "from") String sortBy){

        return showService.findAllPaged(pageNo,pageSize,sortBy);

    }

    @PostMapping("/admin/create")
    public ResponseEntity<Show> createShow(@RequestBody Show show) {
        Scene s = sceneRepository.findById(new SceneId("66a715de-fc23-466b-925b-789ffb08192d")).orElseThrow(RuntimeException::new);
        show.setScene(s);
        return ResponseEntity.ok().body(this.showService.createShow(show));
    }

    @PutMapping("/admin/edit/{id}")
    public ResponseEntity<Show> updateShow(@PathVariable String id, @RequestBody Show show){
        return ResponseEntity.ok().body(this.showService.updateShow(id, show));
    }

    @PutMapping("/admin/delete/{showId}")
    public HttpStatus deleteShow(@PathVariable String showId) {
        this.showService.deleteShow(showId);
        return HttpStatus.OK;
    }

}
