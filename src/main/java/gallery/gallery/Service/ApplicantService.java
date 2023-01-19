package gallery.gallery.Service;

import gallery.gallery.auth.config.user.UserRepository;
import gallery.gallery.domain.Applicant;
import gallery.gallery.domain.Art;
import gallery.gallery.domain.User;
import gallery.gallery.dto.ApplicantDto;
import gallery.gallery.repository.ApplicantRepository;
import gallery.gallery.repository.ArtRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicantService {
    private final ApplicantRepository applicantRepository;
    private final UserRepository userRepository;
    private final ArtRepository artRepository;

    public ApplicantDto saveApplicant(ApplicantDto applicantDto) throws Exception {
        /** 해당 User, Art 찾기*/
        Optional<User> foundUser = userRepository.findById(applicantDto.getUser_id());
        Optional<Art> foundArt = artRepository.findById(applicantDto.getArt_id());

        if(foundUser.isEmpty() || foundArt.isEmpty()){
            throw new Exception("해당 user 또는 art가 존재하지 않습니다.");

        }
            User user = foundUser.get();
            Art art = foundArt.get();
            Applicant applicant = applicantDto.toEntity(applicantDto, user, art);
            Applicant savedApplicant = applicantRepository.save(applicant);

            return ApplicantDto.of(savedApplicant);
    }
}
