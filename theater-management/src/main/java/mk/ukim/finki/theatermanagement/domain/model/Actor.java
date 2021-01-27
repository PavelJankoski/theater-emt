package mk.ukim.finki.theatermanagement.domain.model;

import lombok.Getter;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.sharedkernel.domain.name.FullName;

import javax.persistence.*;


@Entity
@Table(name = "actors")
@Getter
public class Actor extends AbstractEntity<ActorId> {
    @EmbeddedId
    private ActorId id;

    @Version
    private Long version;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "firstName", column = @Column(name = "first_name", nullable = false)),
            @AttributeOverride(name = "lastName", column = @Column(name = "last_name",nullable = false))
    })
    private FullName fullName;
}
