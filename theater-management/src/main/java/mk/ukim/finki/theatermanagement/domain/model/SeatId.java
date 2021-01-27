package mk.ukim.finki.theatermanagement.domain.model;

import mk.ukim.finki.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class SeatId extends DomainObjectId {
    public SeatId(String id) {
        super(id);
    }

    @SuppressWarnings("unused")
    protected SeatId() {
        super("");
    }
}
