package gallery.gallery.general.dto;

import gallery.gallery.general.domain.WishList;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WishListDto {
    private Long userId;
    private Long artId;
    @Builder
    public WishListDto(Long userId, Long artId) {
        this.userId = userId;
        this.artId = artId;
    }
    public static WishListDto of(WishList wishList){
        return WishListDto.builder()
                .userId(wishList.getUser().getId())
                .artId(wishList.getArt().getId())
                .build();
    }
}
