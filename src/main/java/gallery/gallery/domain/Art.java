package gallery.gallery.domain;

import gallery.gallery.common.enums.Selling;
import gallery.gallery.dto.ArtDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "ARTS")
public class Art {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "art_id")
    private Long id;

    @Column(nullable = false , length = 50)
    private String author;

    @Column(nullable = false, length = 50)
    private String art_name;

    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime reg_date;

    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime closed_date;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "is_selling", nullable = false)
    private Selling isSelling;

    @Builder
    public Art(Long id, String author, String art_name, LocalDateTime reg_date, LocalDateTime closed_date, Selling isSelling) {
        this.id = id;
        this.author = author;
        this.art_name = art_name;
        this.reg_date = reg_date;
        this.closed_date = closed_date;
        this.isSelling = isSelling;
    }

       public void update(ArtDto artDto){
        if(!artDto.getAuthor().isEmpty())
            this.author = artDto.getAuthor();
        if(!artDto.getArt_name().isEmpty())
            this.art_name = artDto.getArt_name();
        if(!artDto.getReg_date().equals(null))
            this.reg_date = artDto.getReg_date();
        if(!artDto.getClosed_date().equals(null))
            this.closed_date = artDto.getClosed_date();
        if(!artDto.getIs_selling().equals(null))
            this.isSelling = artDto.getIs_selling();
    }

}
