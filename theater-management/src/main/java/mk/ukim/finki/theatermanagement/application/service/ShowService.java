package mk.ukim.finki.theatermanagement.application.service;

import mk.ukim.finki.theatermanagement.domain.model.Scene;
import mk.ukim.finki.theatermanagement.domain.model.Show;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ShowService {

    Page<Show> findAllPaged(Integer pageNo, Integer pageSize, String sortBy);

    List<Show> findAll();

    Show findShowById(String id);

    List<Show> searchShow(String term);

    Show createShow(Show show);

    Show updateShow(String id, Show show);

    void deleteShow(String id);
}
