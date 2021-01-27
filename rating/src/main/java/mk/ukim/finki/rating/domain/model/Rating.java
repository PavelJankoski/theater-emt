package mk.ukim.finki.rating.domain.model;


import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "rating")
public class Rating extends AbstractEntity<RatingId> {

    @EmbeddedId
    private RatingId id;

    @Version
    private Long version;

    private Float rating;

    @Embedded
    @AttributeOverride(name="id",column = @Column(name="user_id",nullable = false))
    private UserId userId;

    @Embedded
    @AttributeOverride(name="id",column = @Column(name="show_id",nullable = false))
    private ShowId showId;

}
