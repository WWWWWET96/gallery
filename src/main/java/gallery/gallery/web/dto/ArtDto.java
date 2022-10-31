package gallery.gallery.web.dto;

import gallery.gallery.common.Enum.Selling;
import gallery.gallery.domain.art.entity.Art;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ArtDto {
    private String author;
    private String artname;
    private LocalDateTime reg_date;
    private LocalDateTime closed_date;
    private Selling is_selling;
    @Builder
    public ArtDto(String author, String artname, LocalDateTime reg_date, LocalDateTime closed_date, Selling is_selling) {
        this.author = author;
        this.artname = artname;
        this.reg_date = reg_date;
        this.closed_date = closed_date;
        this.is_selling = is_selling;
    }

    public Art toEntity(){
        return Art.builder()
                .author(author)
                .artName(artname)
                .reg_date(reg_date)
                .closed_date(closed_date)
                .isSelling(is_selling)
                .build();
    }
}
