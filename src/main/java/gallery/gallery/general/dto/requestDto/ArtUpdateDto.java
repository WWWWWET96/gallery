package gallery.gallery.general.dto.requestDto;

import gallery.gallery.common.enums.Selling;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ArtUpdateDto {
    private LocalDateTime regDate;
    private LocalDateTime closedDate;
    private Selling isSelling;
}
