package mk.ukim.finki.theatermanagement.domain.repository;

import mk.ukim.finki.theatermanagement.domain.model.Actor;
import mk.ukim.finki.theatermanagement.domain.model.ActorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActorRepository extends JpaRepository<Actor, ActorId> {
    List<Actor> findByIsDeletedFalse();

    Optional<Actor> findByIdAndIsDeletedFalse(ActorId id);
}
