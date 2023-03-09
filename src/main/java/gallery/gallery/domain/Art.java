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
@Table(name = "art")
public class Art {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "art_id")
    private Long id;

    @Column(nullable = false , length = 50)
    private String author;

    @Column(nullable = false, length = 50)
    private String artName;

    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime regDate;

    @Column(nullable = false, columnDefinition = "TIMESTAMP")
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

       public void update(ArtDto artDto){
        if(!artDto.getAuthor().isEmpty())
            this.author = artDto.getAuthor();
        if(!artDto.getArtName().isEmpty())
            this.artName = artDto.getArtName();
        if(!artDto.getRegDate().equals(null))
            this.regDate = artDto.getRegDate();
        if(!artDto.getClosedDate().equals(null))
            this.closedDate = artDto.getClosedDate();
        if(!artDto.getIs_selling().equals(null))
            this.isSelling = artDto.getIs_selling();
    }

}
