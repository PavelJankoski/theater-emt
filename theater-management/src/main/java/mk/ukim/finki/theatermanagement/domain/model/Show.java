package mk.ukim.finki.theatermanagement.domain.model;

import lombok.Getter;
import lombok.Setter;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.sharedkernel.domain.financial.Money;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "shows")
@Getter
@Setter
public class Show extends AbstractEntity<ShowId> {
    @EmbeddedId
    private ShowId id;

    @Version
    private Long version;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(name = "set_designer")
    private String setDesigner;

    @Column(name = "costume_designer")
    private String costumeDesigner;

    @Column(name = "starting_date_and_time", nullable = false)
    private LocalDateTime from;

    @Column(nullable = false)
    private int duration;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @Lob
    private byte[] image;

    @ManyToMany(targetEntity = Actor.class, fetch = FetchType.EAGER)
    Set<Actor> actors = new HashSet<>();

    @ManyToOne
    private Scene scene;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "price")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency"))
    })
    private Money ticketPrice;


    

}
