package gallery.gallery.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WishListDto {

    private Long user_id;
    private Long art_id;

    @Builder
    public WishListDto(Long user_id, Long art_id) {
        this.user_id = user_id;
        this.art_id = art_id;
    }


}
