package gallery.gallery.general.controller;

import java.util.List;
import gallery.gallery.general.service.ApplicantService;
import gallery.gallery.general.dto.ApplicantDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/applicants")
public class ApplicantController {
    private final ApplicantService applicantService;
//    @PostMapping
//    public ResponseEntity<ApplicantDto> saveApplicant(@RequestBody ApplicantDto applicantDto) throws Exception {
//        ApplicantDto response = applicantService.saveApplicant(applicantDto);
//
//        return ResponseEntity.ok(response);
//    }
    @PostMapping
    public ResponseEntity<ApplicantDto> saveApplicant(Long userId, Long artId, Long price) throws Exception {
        ApplicantDto response = applicantService.saveApplicant(userId, artId, price);

        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApplicantDto> findById(@PathVariable("id") Long id) throws Exception {
        ApplicantDto response = applicantService.findById(id);
        return ResponseEntity.ok(response);
    }
    @GetMapping
    public ResponseEntity<List<ApplicantDto>> findAll() throws Exception {
        List<ApplicantDto> responses = applicantService.findAll();

        return ResponseEntity.ok(responses);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<ApplicantDto> updatePrice(@PathVariable("id") Long id, Long price) throws Exception {
        ApplicantDto response = applicantService.updatePrice(id, price);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id){
        applicantService.deleteById(id);
    }
}
