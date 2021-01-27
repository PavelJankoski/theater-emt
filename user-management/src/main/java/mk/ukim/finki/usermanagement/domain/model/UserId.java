package mk.ukim.finki.usermanagement.domain.model;

import mk.ukim.finki.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class UserId extends DomainObjectId {

    public UserId(String id) {
        super(id);
    }
}
