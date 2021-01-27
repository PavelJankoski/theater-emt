package mk.ukim.finki.usermanagement.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.sharedkernel.domain.base.DomainObjectId;
import mk.ukim.finki.sharedkernel.domain.name.FullName;

import javax.persistence.*;
import java.beans.Customizer;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
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

    @ManyToOne
    private Role role;

    @SuppressWarnings("unused")
    protected User() {

    }

    private User(FullName name, String email, String password, Role role) {
        this.id = DomainObjectId.randomId(UserId.class);
        this.fullName = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }


    public static User createUser(FullName name, String email, String password, Role role) {
        return new User(name, email, password, role);
    }


    public UserId getId() {
        return id;
    }

    public long getVersion() {
        return version;
    }

    public FullName getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }
}
