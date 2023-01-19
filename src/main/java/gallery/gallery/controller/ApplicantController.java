package gallery.gallery.controller;


import gallery.gallery.Service.ApplicantService;
import gallery.gallery.dto.ApplicantDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApplicantController {

    private final ApplicantService applicantService;

    @PostMapping("/applicant/save")
    public ResponseEntity<ApplicantDto> saveApplicant(@RequestBody ApplicantDto applicantDto) throws Exception {
        ApplicantDto response = applicantService.saveApplicant(applicantDto);

        return ResponseEntity.ok(response);
    }
}
