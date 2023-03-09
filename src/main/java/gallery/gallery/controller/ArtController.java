package gallery.gallery.controller;
import gallery.gallery.service.ArtService;
import gallery.gallery.dto.ArtDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
    public ResponseEntity<List<ArtDto>> findArtByAll(){
        List<ArtDto> responses = artService.findArtByAll();
        return ResponseEntity.ok(responses);
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
