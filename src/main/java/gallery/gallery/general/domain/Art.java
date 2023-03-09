package gallery.gallery.general.domain;

import gallery.gallery.common.enums.Selling;
import gallery.gallery.general.dto.ArtDto;
import gallery.gallery.general.dto.requestDto.ArtUpdateDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "art")
public class Art {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "art_id")
    private Long id;

    @Column(nullable = false, length = 50)
    private String author;

    @Column(name = "art_name", nullable = false, length = 50)
    private String artName;

    @Column(name = "price", nullable = false,length = 100)
    private Long price = Long.valueOf(0); //현재 입찰가 기본 가격 0원. 더 큰 가격 나올 때마다 갱신

    @Column(name = "contents", nullable = false, length = 255)
    private String contents; //작품 소개

    @Column(name = "year",nullable = false, length = 50)
    private int year;//작품 년도
    @Column(name = "reg_date", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime regDate;

    @Column(name = "closed_date", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime closedDate;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "is_selling", nullable = false)
    private Selling isSelling;
    @Builder
    public Art(Long id, String author, String artName, Long price, String contents, int year, LocalDateTime regDate, LocalDateTime closedDate, Selling isSelling) {
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
    public void update(ArtUpdateDto artUpdateDto) {
        this.artName = artUpdateDto.getArtName();
        this.author = artUpdateDto.getAuthor();
        this.price = artUpdateDto.getPrice();
        this.contents = artUpdateDto.getContents();
        this.year = artUpdateDto.getYear();
        this.regDate = artUpdateDto.getRegDate();
        this.closedDate = artUpdateDto.getClosedDate();
        this.isSelling = artUpdateDto.getIsSelling();
    }
}
