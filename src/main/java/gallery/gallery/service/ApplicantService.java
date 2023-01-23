package gallery.gallery.service;

import java.util.List;
import gallery.gallery.auth.config.user.UserRepository;
import gallery.gallery.domain.Applicant;
import gallery.gallery.domain.Art;
import gallery.gallery.domain.User;
import gallery.gallery.dto.ApplicantDto;
import gallery.gallery.repository.ApplicantRepository;
import gallery.gallery.repository.ArtRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApplicantService {
    private final ApplicantRepository applicantRepository;
    private final UserRepository userRepository;
    private final ArtRepository artRepository;
    @Transactional
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

    public ApplicantDto findById(Long id) throws Exception {
        Optional<Applicant> foundApplicant = applicantRepository.findById(id);
        if(foundApplicant.isEmpty()){
            throw new Exception("해당 신청서는 없습니다.");
        }
        Applicant applicant = foundApplicant.get();

        return ApplicantDto.of(applicant);
    }
    public List<ApplicantDto> findAll() throws Exception {
        List<Applicant> applicants = applicantRepository.findAll();
        if(applicants.isEmpty()){
            throw new Exception("존재하는 신청서가 없습니다.");
        }
        return applicants.stream().map(ApplicantDto::of).collect(Collectors.toList());
    }

    /** 넣을 수 없는 금액에 대한 validation은 service에서? 엔티티에서?*/
    /**문제점 : price수정가격이 db에 반영이 안됨*/
    @Transactional
    public ApplicantDto updatePrice(Long id, Long price) throws Exception {
        Optional<Applicant> foundApplicant = applicantRepository.findById(id);
        if(foundApplicant.isEmpty()){
            throw new Exception("해당 신청서는 없습니다.");
        }
        if(price <= 0){
            throw new Exception("신청할 수 없는 금액입니다.");
        }
        Applicant applicant = foundApplicant.get();
        applicant.updatePrice(price);
        return ApplicantDto.of(applicant);
    }

    @Transactional
    public void deleteById(Long id){
        applicantRepository.deleteById(id);
    }
}
