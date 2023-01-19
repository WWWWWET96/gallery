package gallery.gallery.dto;

import gallery.gallery.common.Enum.Selling;
import gallery.gallery.domain.Art;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
/**
 * 나중에 reg_date가 closed_date보다 빨라야한다는 유효성체크부분 넣어야함
 * 수정할 때도 이 두 개의 관계가 이뤄지게
 * */
@Getter
@NoArgsConstructor
public class ArtDto {
    private Long id;
    private String author;
    private String art_name;
    private LocalDateTime reg_date;
    private LocalDateTime closed_date;
    private Selling is_selling;

    @Builder
    public ArtDto(Long id, String author, String art_name, LocalDateTime reg_date, LocalDateTime closed_date, Selling is_selling) {
        this.id = id;
        this.author = author;
        this.art_name = art_name;
        this.reg_date = reg_date;
        this.closed_date = closed_date;
        this.is_selling = is_selling;
    }
    public Art toEntity(ArtDto artDto){
        return Art.builder()
                .art_name(artDto.getArt_name())
                .author(artDto.getAuthor())
                .reg_date(artDto.getReg_date())
                .closed_date(artDto.getClosed_date())
                .isSelling(artDto.getIs_selling())
                .build();
    }

    /**여기서 static이여야만 하는 이유는?*/
    public static ArtDto of(Art art){
        return ArtDto.builder()
                .id(art.getId())
                .author(art.getAuthor())
                .art_name(art.getArt_name())
                .reg_date(art.getReg_date())
                .closed_date(art.getClosed_date())
                .is_selling(art.getIsSelling())
                .build();
    }
}
