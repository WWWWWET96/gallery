package gallery.gallery.service;

<<<<<<< main:src/main/java/gallery/gallery/service/ArtService.java
import gallery.gallery.common.error.RestApiException;
=======
import gallery.gallery.common.enums.Selling;
import gallery.gallery.common.error.exception.RestApiException;
>>>>>>> feat: [art] 페이징 기능(#17):src/main/java/gallery/gallery/general/service/ArtService.java
import gallery.gallery.common.error.errorCode.CommonErrorCode;
import gallery.gallery.domain.Art;
import gallery.gallery.dto.ArtDto;
import gallery.gallery.repository.ArtRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ArtService {
    private final ArtRepository artRepository;
    /**
     * 그림작품인 art에 관한 글을 저장하는 로직
     */
    @Transactional
    public ArtDto saveArt(ArtDto artDto) {
        Art forSave = artDto.toEntity(artDto);
        Art saved = artRepository.save(forSave);

        return ArtDto.of(saved);
    }

    /**
     * 그림작품인 art에 관한 글을 하나 조회하는 로직
     */
    public ArtDto findArtById(Long id) {
        Art foundArt = artRepository.findById(id).orElseThrow(
                () -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND)
        );

        return ArtDto.of(foundArt);
    }

    /**
     * 그림작품인 art에 관한 글을 전체 조회하는 로직
     */
    public List<ArtDto> findArtByAll(String searchType, String searchKeyword, Pageable pageable) {
        Page<Art> artList = null;
        if(searchKeyword == null){
            artList = artRepository.findAll(pageable);
        }
        else if(searchType.equals("author")){
          artList = artRepository.findByAuthorContaining(searchKeyword, pageable);
        }
        else if(searchType.equals("art-name")){
             artList = artRepository.findByArtNameContaining(searchKeyword, pageable);
        }
        else if(searchType.equals("is-selling")){
            Selling convertSearchKeyword = stringToEnumConverter(searchKeyword);
            artList = artRepository.findByIsSelling(convertSearchKeyword, pageable);
        }
        if (artList.isEmpty()) {
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);
        }
        return artList.getContent().stream()
                .map(ArtDto::of).collect(Collectors.toList());
    }

    /**
     * 그림작품인 art에 관한 글을 수정하는 로직
     */@Transactional
    public ArtDto updateArt(ArtDto artDto, Long id) {
         Art foundArt = artRepository.findById(id).orElseThrow(
                 () -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND)
         );
         foundArt.update(artDto);

        return ArtDto.of(foundArt);
    }

    /**
     * 그림작품인 art에 관한 글을 삭제하는 로직
     */
    @Transactional
    public void deleteArtById(Long id) {
        boolean isExist = artRepository.existsById(id);

        if (!isExist) {
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);
        }
        artRepository.deleteById(id);
    }

    private Selling stringToEnumConverter(String searchKeyword){
        if(!Arrays.stream(Selling.values()).equals(searchKeyword)){
            throw new RestApiException(CommonErrorCode.INVALID_PARAMETER);
        }
        return Selling.valueOf(searchKeyword);
    }
}
