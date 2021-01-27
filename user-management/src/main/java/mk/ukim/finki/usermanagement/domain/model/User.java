package mk.ukim.finki.usermanagement.domain.model;

import lombok.Getter;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.sharedkernel.domain.name.FullName;

import javax.persistence.*;
import java.beans.Customizer;

@Entity
@Table(name = "users")
@Getter
public class User extends AbstractEntity<UserId> {

    @EmbeddedId
    private UserId id;

    @Version
    private long version;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "firstName", column = @Column(name = "first_name", nullable = false)),
            @AttributeOverride(name = "lastName", column = @Column(name = "last_name",nullable = false))
    })
    private FullName fullName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;


}
