package gallery.gallery.general.service;

import java.util.List;

import gallery.gallery.auth.repository.UserRepository;
import gallery.gallery.common.error.exception.AlreadyExistedException;
import gallery.gallery.common.error.exception.RestApiException;
import gallery.gallery.common.error.errorCode.CommonErrorCode;
import gallery.gallery.general.domain.Applicant;
import gallery.gallery.general.domain.Art;
import gallery.gallery.general.domain.User;
import gallery.gallery.general.dto.ApplicantDto;
import gallery.gallery.general.repository.ApplicantRepository;
import gallery.gallery.general.repository.ArtRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApplicantService {
    private final ApplicantRepository applicantRepository;
    private final UserRepository userRepository;
    private final ArtRepository artRepository;

//    @Transactional
//    public ApplicantDto saveApplicant(ApplicantDto applicantDto) throws Exception {
//        /** 해당 User, Art 찾기*/
//        User foundUser = userRepository.findById(applicantDto.getUserId()).orElseThrow(
//                () -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND)
//        );
//        Art foundArt = artRepository.findById(applicantDto.getArtId()).orElseThrow(
//                () -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND)
//        );
//        Applicant applicant = applicantDto.toEntity(applicantDto, foundUser, foundArt);
//        Applicant savedApplicant = applicantRepository.save(applicant);
//
//        return ApplicantDto.of(savedApplicant);
//    }
    @Transactional
    public ApplicantDto saveApplicant(Long userId, Long artId, Long price) {
        /** 해당 User, Art 찾기*/
        User foundUser = userRepository.findById(userId).orElseThrow(
                () -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND)
        );
        Art foundArt = artRepository.findById(artId).orElseThrow(
                () -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND)
        );

        if(applicantRepository.existsApplicant(userId, artId)){
            throw new AlreadyExistedException(CommonErrorCode.DUPLICATE_RESOURCE);
        }

        Applicant applicant = Applicant.builder()
                .user(foundUser)
                .art(foundArt)
                .price(price)
                .build();

        Applicant savedApplicant = applicantRepository.save(applicant);

        return ApplicantDto.of(savedApplicant);
    }

    public ApplicantDto findById(Long id) {
        Applicant foundApplicant = applicantRepository.findById(id).orElseThrow(
                () -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND)
        );
        return ApplicantDto.of(foundApplicant);
    }

    public List<ApplicantDto> findAll()  {
        List<Applicant> applicants = applicantRepository.findAll();
        if (applicants.isEmpty()) {
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);
        }
        return applicants.stream().map(ApplicantDto::of).collect(Collectors.toList());
    }

    /** 넣을 수 없는 금액에 대한 validation은 service에서? 엔티티에서?*/
    @Transactional
    public ApplicantDto updatePrice(Long id, Long price) throws Exception {
        Applicant foundApplicant = applicantRepository.findById(id).orElseThrow(
                () -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND)
        );
        if (price <= 0) {
            throw new Exception("신청할 수 없는 금액입니다.");
        }
        foundApplicant.updatePrice(price);
        return ApplicantDto.of(foundApplicant);
    }

    @Transactional
    public void deleteById(Long id) {
        boolean isExist = applicantRepository.existsById(id);
        if(!isExist){
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);
        }
        applicantRepository.deleteById(id);
    }
}
