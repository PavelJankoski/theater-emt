package mk.ukim.finki.theatermanagement.application.service.impl;

import mk.ukim.finki.sharedkernel.domain.base.DomainObjectId;
import mk.ukim.finki.theatermanagement.application.service.ActorService;
import mk.ukim.finki.theatermanagement.domain.exceptions.ActorNotFoundException;
import mk.ukim.finki.theatermanagement.domain.model.Actor;
import mk.ukim.finki.theatermanagement.domain.model.ActorId;
import mk.ukim.finki.theatermanagement.domain.repository.ActorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorServiceImpl implements ActorService {
    private final ActorRepository actorRepository;

    public ActorServiceImpl(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }


    @Override
    public List<Actor> findAll() {
        return actorRepository.findByIsDeletedFalse();
    }

    @Override
    public Actor findActorById(String id) {
        return actorRepository.findByIdAndIsDeletedFalse(new ActorId(id)).orElseThrow(ActorNotFoundException::new);
    }

    @Override
    public void deleteActorById(String id) {
        Actor actor = findActorById(id);
        actor.setIsDeleted(true);
        this.actorRepository.save(actor);
    }

    @Override
    public Actor save(Actor actor) {
        actor.setId(DomainObjectId.randomId(ActorId.class));
        return actorRepository.save(actor);
    }
}
