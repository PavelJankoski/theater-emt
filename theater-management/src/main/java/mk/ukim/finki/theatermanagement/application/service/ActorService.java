package mk.ukim.finki.theatermanagement.application.service;

import mk.ukim.finki.theatermanagement.domain.model.Actor;

import java.util.List;

public interface ActorService {
    List<Actor> findAll();

    Actor findActorById(String id);

    void deleteActorById(String id);

    Actor save(Actor actor);
}
