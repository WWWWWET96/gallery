package gallery.gallery.controller;
import gallery.gallery.service.ArtService;
import gallery.gallery.dto.ArtDto;
import java.util.List;

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
@RequestMapping("/art")
public class ArtController {
    private final ArtService artService;

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
    public ResponseEntity<ArtDto> updateArt(@RequestBody ArtDto artDto, @PathVariable("id") Long id ){
        ArtDto response = artService.updateArt(artDto, id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public void deletArtById(@PathVariable("id") Long id){
        artService.deleteArtById(id);
    }

}
