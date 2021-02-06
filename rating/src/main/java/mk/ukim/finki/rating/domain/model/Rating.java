package mk.ukim.finki.rating.domain.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ratings")
@Getter
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

    public Rating(UserId userId, ShowId showId, float rating) {
        this.id = DomainObjectId.randomId(RatingId.class);
        this.userId = userId;
        this.showId = showId;
        this.rating = rating;
    }

    public Rating(){}
}
