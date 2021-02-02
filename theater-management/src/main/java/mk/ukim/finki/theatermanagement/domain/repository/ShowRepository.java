package mk.ukim.finki.theatermanagement.domain.repository;

import mk.ukim.finki.theatermanagement.domain.model.Show;
import mk.ukim.finki.theatermanagement.domain.model.ShowId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowRepository extends PagingAndSortingRepository<Show, ShowId> {
    List<Show> findAllByIsDeletedOrderByFrom(Boolean isDeleted);

    List<Show> findByTitleOrDescriptionContainingIgnoreCaseAndIsDeletedFalseOrderByFrom(String title, String description);

}
