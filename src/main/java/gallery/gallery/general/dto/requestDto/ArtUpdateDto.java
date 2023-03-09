package gallery.gallery.general.dto.requestDto;

import gallery.gallery.common.enums.Selling;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ArtUpdateDto {
    private String author;
    private String artName;
    private Long price;
    private String contents;
    private int year;
    private LocalDateTime regDate;
    private LocalDateTime closedDate;
    private Selling isSelling;
}
