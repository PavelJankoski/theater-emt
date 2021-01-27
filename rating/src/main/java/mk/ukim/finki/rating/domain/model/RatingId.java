package mk.ukim.finki.rating.domain.model;

import mk.ukim.finki.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class RatingId extends DomainObjectId {

    public RatingId(String id) {
        super(id);
    }

    @SuppressWarnings("unused")
    protected RatingId(){
        super("");
    }
}
