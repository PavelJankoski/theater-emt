package mk.ukim.finki.usermanagement.domain.model;

import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Version;
import java.beans.Customizer;

public class User extends AbstractEntity<UserId> {

    @EmbeddedId
    private UserId userId;

    @Version
    private long version;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;


}
