package mk.ukim.finki.theatermanagement.domain.model;

import mk.ukim.finki.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class ActorId extends DomainObjectId {

    public ActorId(String id) {
        super(id);
    }

    @SuppressWarnings("unused")
    protected ActorId() {
        super("");
    }
}
