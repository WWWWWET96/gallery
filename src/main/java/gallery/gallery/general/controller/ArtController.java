package gallery.gallery.general.controller;
import java.util.List;

import gallery.gallery.common.error.errorCode.CommonErrorCode;
import gallery.gallery.common.error.exception.NotEqualException;
import gallery.gallery.common.error.exception.RestApiException;
import gallery.gallery.general.dto.WishListDto;
import gallery.gallery.general.dto.requestDto.ArtUpdateDto;
import gallery.gallery.general.service.ArtService;
import gallery.gallery.general.dto.ArtDto;
import gallery.gallery.general.service.WishListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/arts")
public class ArtController {
    private final ArtService artService;
    private final WishListService wishListService;

    @PostMapping
    public ResponseEntity<ArtDto> saveArt(@RequestBody ArtDto artDto){
        ArtDto response = artService.saveArt(artDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtDto> findArtById(@PathVariable("id") Long id){
        ArtDto response = artService.findArtById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}")
    public ResponseEntity<WishListDto> wishListById(@PathVariable("id") Long id, @RequestBody WishListDto wishListDto)
    {/**
     * pathVaiable로 받은 id와 wishListDto.artId가 같지 않을 경우 exception 발생
     * */
        if(wishListDto.getArtId() != id){
            throw new NotEqualException(CommonErrorCode.DATA_NOT_THE_SAME);
        }
        WishListDto response = wishListService.saveWishList(wishListDto);
        return ResponseEntity.ok(response);

    }

    @GetMapping
    public ResponseEntity<List<ArtDto>> findArtByAll(@PageableDefault(size = 5, sort = "id",
            direction = Sort.Direction.DESC) Pageable pageable,
                                                     @RequestParam(required = false)String searchKeyword
            ,@RequestParam(required = false)String searchType){

        /**
         * 질문: 페이징할 때 return타입이 굳이 Page<>가아니여도 되는건가? </>*/
        List<ArtDto> response = artService.findArtByAll(searchType, searchKeyword, pageable);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ArtDto> updateArt(@RequestBody ArtUpdateDto artUpdateDto, @PathVariable("id") Long id ){
        ArtDto response = artService.updateArt(artUpdateDto, id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public void deletArtById(@PathVariable("id") Long id){
        artService.deleteArtById(id);
    }

}
