package gallery.gallery.controller;

import java.util.List;
import gallery.gallery.service.ApplicantService;
import gallery.gallery.dto.ApplicantDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequiredArgsConstructor
@RequestMapping("/applicant")
public class ApplicantController {
    private final ApplicantService applicantService;
    @PostMapping
<<<<<<< main:src/main/java/gallery/gallery/controller/ApplicantController.java
    public ResponseEntity<ApplicantDto> saveApplicant(@RequestBody ApplicantDto applicantDto) throws Exception {
        ApplicantDto response = applicantService.saveApplicant(applicantDto);

=======
    public ResponseEntity<ApplicantDto> saveApplicant(Long userId, Long artId, Long price) throws Exception {
        ApplicantDto response = applicantService.saveApplicant(userId, artId, price);
>>>>>>> feat: [art] 페이징 기능(#17):src/main/java/gallery/gallery/general/controller/ApplicantController.java
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApplicantDto> findById(@PathVariable("id") Long id) throws Exception {
        ApplicantDto foundApplicant = applicantService.findById(id);
        return ResponseEntity.ok(foundApplicant);
    }
    @GetMapping
    public ResponseEntity<List<ApplicantDto>> findAll() throws Exception {
        List<ApplicantDto> applicants = applicantService.findAll();

        return ResponseEntity.ok(applicants);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<ApplicantDto> updatePrice(@PathVariable("id") Long id, Long price) throws Exception {
        ApplicantDto updatedApplicant = applicantService.updatePrice(id, price);
        return ResponseEntity.ok(updatedApplicant);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id){
        applicantService.deleteById(id);
    }
}
