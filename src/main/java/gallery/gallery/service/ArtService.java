package gallery.gallery.service;

import gallery.gallery.common.error.EntityNotFoundException;
import gallery.gallery.domain.Art;
import gallery.gallery.dto.ArtDto;
import gallery.gallery.repository.ArtRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Optional<Art> byId = artRepository.findById(id);
        if (byId.isEmpty()) {
            throw new EntityNotFoundException("해당 게시글이 없습니다.");
        }
        Art findArt = byId.get(); // Optional객체에서 꺼내기

        return ArtDto.of(findArt);
    }

    /**
     * 그림작품인 art에 관한 글을 전체 조회하는 로직
     */
    public List<ArtDto> findArtByAll() {
        List<Art> artList = artRepository.findAll();
        if (artList.isEmpty()) {
            throw new EntityNotFoundException("존재하는 게시글이 없습니다.");
        }

        return artList.stream().map(ArtDto::of).collect(Collectors.toList());
    }

    /**
     * 그림작품인 art에 관한 글을 수정하는 로직
     */@Transactional
    public ArtDto updateArt(ArtDto artDto, Long id) {
        Optional<Art> byId = artRepository.findById(id);
        if (byId.isEmpty()) {
            throw new EntityNotFoundException("해당 게시글이 없습니다.");
        }
        Art findArt = byId.get();
        findArt.update(artDto);

        return ArtDto.of(findArt);
    }

    /**
     * 그림작품인 art에 관한 글을 삭제하는 로직
     */
    @Transactional
    public void deleteArtById(Long id) {
        boolean isExist = artRepository.existsById(id);

        if (!isExist) {
            throw new EntityNotFoundException("해당 게시글이 없습니다.");
        }
        artRepository.deleteById(id);
    }
}
