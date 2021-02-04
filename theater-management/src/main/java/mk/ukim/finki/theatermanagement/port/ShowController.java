package mk.ukim.finki.theatermanagement.port;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import mk.ukim.finki.theatermanagement.application.service.ShowService;
import mk.ukim.finki.theatermanagement.domain.model.Show;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/shows")
public class ShowController {
    private final ShowService showService;
    private final ObjectMapper objectMapper;
    public ShowController(ShowService showService, ObjectMapper objectMapper) {
        this.showService = showService;
        this.objectMapper = objectMapper;
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

    @PostMapping(value = "/admin/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Show> createShow(@RequestPart String show, @RequestPart MultipartFile image) throws IOException {
        objectMapper.registerModule(new JavaTimeModule());
        Show s = objectMapper.readValue(show, Show.class);
        s.setImage(image.getBytes());
        return ResponseEntity.ok().body(this.showService.createShow(s));
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
