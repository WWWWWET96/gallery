package gallery.gallery.domain;

import gallery.gallery.common.base.BaseEntity;
import gallery.gallery.dto.ApplicantDto;
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
@Table(name = "APPLICANTS")
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

    /**
     * 지금 문제인거: ApplicantDto에서 user_id, art_id로 되어있는데 이걸 Applicant의 user, art로 연결시켜야함
     * */
    public static Applicant of( User user, Art art, ApplicantDto applicantDto){
       return Applicant.builder()
               .user(user)
               .art(art)
               .price(applicantDto.getPrice())
               .build();
    }
}