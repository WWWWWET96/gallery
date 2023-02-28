package gallery.gallery.general.domain;

import gallery.gallery.common.base.BaseEntity;
import lombok.Builder;
import lombok.Getter;
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
@Getter
@Table(name = "applicant")
public class Applicant extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "applicant_id")
    private Long id;
    //@JoinColumn: 엔티티간 조인과는 관계없이 외래키 이름 지정을 위해서 사용됨. 생략가능

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "art_id")
    @NotNull
    private Art art;

    @Column(name = "price", nullable = false, length = 30)
    private Long price;

    @Builder
    public Applicant(LocalDateTime createdDate, LocalDateTime modifiedDate, Long id, User user, Art art, Long price) {
        super(createdDate, modifiedDate);
        this.id = id;
        this.user = user;
        this.art = art;
        this.price = price;
    }

    public void updatePrice(Long price) {
        this.price = price;
    }
}