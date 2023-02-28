package gallery.gallery.general.domain;

import gallery.gallery.common.base.BaseEntity;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * PK: id
 * FK: user, art
 * */
@Entity
@NoArgsConstructor
@Table(name = "wishlist")
public class WishList extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wishlist_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "art_id")
    @NotNull
    private Art art;

    @Builder
    public WishList(LocalDateTime createdDate, LocalDateTime modifiedDate, Long id, User user, Art art) {
        super(createdDate, modifiedDate);
        this.id = id;
        this.user = user;
        this.art = art;
    }

    public static WishList of(User user, Art art){
        return WishList.builder()
                .user(user)
                .art(art)
                .build();
    }
}
