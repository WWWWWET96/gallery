package gallery.gallery.dto;

import gallery.gallery.domain.Applicant;
import gallery.gallery.domain.Art;
import gallery.gallery.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


//user_id, art_id정보로 해당 applicant찾아서 가격 수정해주기(다른 객체 아니곡 동일 객체의 값 수정으로 진행할 것
@Getter
@NoArgsConstructor
public class ApplicantDto {
    private Long user_id;
    private Long art_id;
    private Long price;

    @Builder
    public ApplicantDto(Long user_id, Long art_id, Long price) {
        this.user_id = user_id;
        this.art_id = art_id;
        this.price = price;
    }
    public Applicant of(User user, Art art){
       return Applicant.builder()
                .user(user)
                .art(art)
                .price(price)
                .build();
    }
}
