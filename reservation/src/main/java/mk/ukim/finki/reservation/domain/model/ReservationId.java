package mk.ukim.finki.reservation.domain.model;

import mk.ukim.finki.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class ReservationId extends DomainObjectId {

    public ReservationId(String id) {
        super(id);
    }

    @SuppressWarnings("unused")
    protected ReservationId(){
        super("");
    }
}
