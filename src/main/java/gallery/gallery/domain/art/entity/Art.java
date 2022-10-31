package gallery.gallery.domain.art.entity;

import gallery.gallery.common.Enum.Selling;
import gallery.gallery.common.base.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "ARTS")
public class Art extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "art_id")
    private Long id;

    @Column(nullable = false , length = 50)
    private String author;

    @Column(name = "art_name", nullable = false, length = 50)
    private String artName;

    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime reg_date;

    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime closed_date;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "is_selling", nullable = false)
    private Selling isSelling;

    @Builder
    public Art(LocalDateTime createdDate, LocalDateTime modifiedDate, Long id, String author, String artName, LocalDateTime reg_date, LocalDateTime closed_date, Selling isSelling) {
        super(createdDate, modifiedDate);
        this.id = id;
        this.author = author;
        this.artName = artName;
        this.reg_date = reg_date;
        this.closed_date = closed_date;
        this.isSelling = isSelling;
    }
}
