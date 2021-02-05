package mk.ukim.finki.theatermanagement.domain.repository;

import mk.ukim.finki.theatermanagement.domain.model.Show;
import mk.ukim.finki.theatermanagement.domain.model.ShowId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShowRepository extends PagingAndSortingRepository<Show, ShowId> {
    Optional<Show> findByIdAndIsDeletedFalse(ShowId id);

    List<Show> findAllByIsDeletedOrderByFrom(Boolean isDeleted);

    List<Show> findByTitleContainingIgnoreCaseAndIsDeletedFalseOrderByFrom(String title);

}
