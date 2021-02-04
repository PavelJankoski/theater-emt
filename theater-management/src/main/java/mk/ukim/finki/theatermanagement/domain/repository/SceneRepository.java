package mk.ukim.finki.theatermanagement.domain.repository;

import mk.ukim.finki.theatermanagement.domain.model.Scene;
import mk.ukim.finki.theatermanagement.domain.model.SceneId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SceneRepository extends JpaRepository<Scene, SceneId> {
    List<Scene> findByIsDeletedFalse();
    Optional<Scene> findByIdAndIsDeletedFalse(SceneId id);

}
