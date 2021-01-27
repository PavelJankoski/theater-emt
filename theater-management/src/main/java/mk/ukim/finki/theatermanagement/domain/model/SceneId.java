package mk.ukim.finki.theatermanagement.domain.model;

import mk.ukim.finki.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class SceneId extends DomainObjectId {

    public SceneId(String id) {
        super(id);
    }

    @SuppressWarnings("unused")
    protected SceneId() {
        super("");
    }
}
