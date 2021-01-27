package mk.ukim.finki.rating.domain.model;

import mk.ukim.finki.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class ShowId extends DomainObjectId {

    public ShowId(String id) {
        super(id);
    }

    @SuppressWarnings("unused")
    protected ShowId(){
        super("");
    }
}
