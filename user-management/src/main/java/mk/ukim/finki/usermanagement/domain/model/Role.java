package mk.ukim.finki.usermanagement.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.usermanagement.domain.enums.ERole;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
public class Role extends AbstractEntity<RoleId> {
    @EmbeddedId
    private RoleId id;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private ERole name;

    @JsonIgnore
    @OneToMany(mappedBy = "role")
    private Set<User> users;

    public RoleId getId() {
        return id;
    }

    public ERole getName() {
        return name;
    }

    public Set<User> getUsers() {
        return users;
    }
}
