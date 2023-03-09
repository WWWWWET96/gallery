package gallery.gallery.general.service;

import gallery.gallery.auth.repository.UserRepository;
import gallery.gallery.common.error.errorCode.CommonErrorCode;
import gallery.gallery.common.error.exception.RestApiException;
import gallery.gallery.general.domain.Art;
import gallery.gallery.general.domain.User;
import gallery.gallery.general.domain.WishList;
import gallery.gallery.general.dto.WishListDto;
import gallery.gallery.general.repository.ArtRepository;
import gallery.gallery.general.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WishListService{
    private final UserRepository userRepository;
    private final ArtRepository artRepository;
    private final WishListRepository wishListRepository;

    private WishList findUserArt(WishListDto wishListDto){
        User foundUser = userRepository.findById(wishListDto.getUserId()).orElseThrow(
                () -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND)
        );
        Art foundArt = artRepository.findById(wishListDto.getArtId()).orElseThrow(
                () -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND)
        );

        return WishList.builder()
                .user(foundUser)
                .art(foundArt)
                .build();
    }
    public WishListDto saveWishList(WishListDto wishListDto){
        WishList saved = wishListRepository.save(findUserArt(wishListDto));
        return WishListDto.of(saved);
    }
}
