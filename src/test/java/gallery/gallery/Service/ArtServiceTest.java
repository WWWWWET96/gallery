package gallery.gallery.Service;

import gallery.gallery.common.Enum.Selling;
import gallery.gallery.domain.Art;
import gallery.gallery.dto.ArtDto;
import gallery.gallery.repository.ArtRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.assertj.core.api.Assertions;

import java.time.LocalDateTime;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class ArtServiceTest {
    @Mock
    private ArtRepository artRepository;
    @InjectMocks
    private ArtService artService;

    @Test
    void join(){
        //given
        ArtDto requestArtDto = ArtDto.builder()
                .author("kim")
                .art_name("lol")
                .reg_date(LocalDateTime.now())
                .closed_date(LocalDateTime.now())
                .is_selling(Selling.BEFORE).build();

        ArtDto savedArt = artService.saveArt(requestArtDto);


        Assertions.assertThat(savedArt.getArt_name()).isEqualTo("lol");
        //Assertions.assertThat(findArt.getAuthor()).isEqualTo("kim");
    }



}