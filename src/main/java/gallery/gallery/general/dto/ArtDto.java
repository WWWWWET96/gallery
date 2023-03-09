package gallery.gallery.general.dto;

import gallery.gallery.common.enums.Selling;
import gallery.gallery.general.domain.Art;
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
    private String artName;
    private Long price;
    private String contents;
    private int year;
    private LocalDateTime regDate;
    private LocalDateTime closedDate;
    private Selling isSelling;
    @Builder
    public ArtDto(Long id, String author, String artName, Long price, String contents, int year, LocalDateTime regDate, LocalDateTime closedDate, Selling isSelling) {
        this.id = id;
        this.author = author;
        this.artName = artName;
        this.price = price;
        this.contents = contents;
        this.year = year;
        this.regDate = regDate;
        this.closedDate = closedDate;
        this.isSelling = isSelling;
    }

    public Art toEntity(ArtDto artDto){
        return Art.builder()
                .artName(artDto.getArtName())
                .author(artDto.getAuthor())
                .price(artDto.getPrice())
                .contents(artDto.getContents())
                .year(artDto.getYear())
                .regDate(artDto.getRegDate())
                .closedDate(artDto.getClosedDate())
                .isSelling(artDto.getIsSelling())
                .build();
    }

    /**여기서 static이여야만 하는 이유는?*/
    public static ArtDto of(Art art){
        return ArtDto.builder()
                .id(art.getId())
                .author(art.getAuthor())
                .artName(art.getArtName())
                .price(art.getPrice())
                .contents(art.getContents())
                .year(art.getYear())
                .regDate(art.getRegDate())
                .closedDate(art.getClosedDate())
                .isSelling(art.getIsSelling())
                .build();
    }
}
