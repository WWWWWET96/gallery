package gallery.gallery.controller;

import java.util.List;
import gallery.gallery.service.ApplicantService;
import gallery.gallery.dto.ApplicantDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ApplicantController {
    private final ApplicantService applicantService;
    @PostMapping("/applicant/save")
    public ResponseEntity<ApplicantDto> saveApplicant(@RequestBody ApplicantDto applicantDto) throws Exception {
        ApplicantDto response = applicantService.saveApplicant(applicantDto);

        return ResponseEntity.ok(response);
    }
    @GetMapping("/applicant/find/{id}")
    public ResponseEntity<ApplicantDto> findById(@PathVariable("id") Long id) throws Exception {
        ApplicantDto foundApplicant = applicantService.findById(id);
        return ResponseEntity.ok(foundApplicant);
    }
    @GetMapping("/applicant/findAll")
    public ResponseEntity<List<ApplicantDto>> findAll() throws Exception {
        List<ApplicantDto> applicants = applicantService.findAll();

        return ResponseEntity.ok(applicants);
    }
    @PatchMapping("/applicant/update/{id}")
    public ResponseEntity<ApplicantDto> updatePrice(@PathVariable("id") Long id, Long price) throws Exception {
        ApplicantDto updatedApplicant = applicantService.updatePrice(id, price);
        return ResponseEntity.ok(updatedApplicant);
    }
    @DeleteMapping("/applicant/delete/{id}")
    public void deleteById(@PathVariable("id") Long id){
        applicantService.deleteById(id);
    }
}
