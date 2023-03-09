package gallery.gallery.dto;

import gallery.gallery.domain.Applicant;
import gallery.gallery.domain.Art;
import gallery.gallery.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


//user_id, art_id정보로 해당 applicant찾아서 가격 수정해주기(다른 객체 아니곡 동일 객체의 값 수정으로 진행할 것
@Getter
@NoArgsConstructor
public class ApplicantDto {
    @NotNull
    private Long applicantId;
    private Long userId;
    private Long artId;
    private Long price;
    @Builder
    public ApplicantDto(Long applicantId, Long userId, Long artId, Long price) {
        this.applicantId = applicantId;
        this.userId = userId;
        this.artId = artId;
        this.price = price;
    }
    public static ApplicantDto of(Applicant applicant){
        return ApplicantDto.builder()
                .applicantId(applicant.getId())
                .userId(applicant.getUser().getId())
                .artId(applicant.getArt().getId())
                .price(applicant.getPrice())
                .build();
    }
    public Applicant toEntity(ApplicantDto applicantDto, User user, Art art){
        return Applicant.builder()
                .id(applicantDto.getApplicantId())
                .user(user)
                .art(art)
                .price(applicantDto.getPrice())
                .build();
    }

}
