package mk.ukim.finki.rating.domain.model;

import mk.ukim.finki.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class UserId extends DomainObjectId {

    public UserId(String id) {
        super(id);
    }

    @SuppressWarnings("unused")
    protected UserId(){
        super("");
    }
}
