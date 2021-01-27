package mk.ukim.finki.usermanagement.domain.model;

import mk.ukim.finki.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class RoleId extends DomainObjectId {
    public RoleId(String id) {
        super(id);
    }

    @SuppressWarnings("unused")
    protected RoleId() {
        super("");
    }
}
