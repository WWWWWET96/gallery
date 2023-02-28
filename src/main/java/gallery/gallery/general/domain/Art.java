package gallery.gallery.general.domain;

import gallery.gallery.common.enums.Selling;
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

    @Column(name = "reg_date", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime regDate;

    @Column(name = "closed_date", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime closedDate;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "is_selling", nullable = false)
    private Selling isSelling;

    @Builder
    public Art(Long id, String author, String artName, LocalDateTime regDate, LocalDateTime closedDate, Selling isSelling) {
        this.id = id;
        this.author = author;
        this.artName = artName;
        this.regDate = regDate;
        this.closedDate = closedDate;
        this.isSelling = isSelling;
    }
    public void update(ArtUpdateDto artUpdateDto) {
        this.regDate = artUpdateDto.getRegDate();
        this.closedDate = artUpdateDto.getClosedDate();
        this.isSelling = artUpdateDto.getIsSelling();
    }

}
