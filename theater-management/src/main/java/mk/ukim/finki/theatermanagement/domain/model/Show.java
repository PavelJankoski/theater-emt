package mk.ukim.finki.theatermanagement.domain.model;

import lombok.Getter;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "shows")
@Getter
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

    @Lob
    private byte[] image;

    @ManyToMany(targetEntity = Actor.class)
    Set<Actor> actors = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "scene_id")
    private Scene scene;

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setId(ShowId id) {
        this.id = id;
    }
}
