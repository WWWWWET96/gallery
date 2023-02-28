package gallery.gallery.repository;

import gallery.gallery.common.enums.Selling;
import gallery.gallery.general.domain.Art;
import gallery.gallery.general.repository.ArtRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import org.assertj.core.api.Assertions;


@SpringBootTest
class ArtRepositoryTest {

    @Autowired
    ArtRepository artRepository;

    @Test
    public void save_test(){
        String author = "kim";
        String artName = "lol";
        LocalDateTime regDate = LocalDateTime.now();
        LocalDateTime closedDate = LocalDateTime.now();
        Selling is_sellling = Selling.BEFORE;

        artRepository.save(Art.builder()
                .author(author)
                .artName(artName)
                .regDate(regDate)
                .closedDate(closedDate)
                .isSelling(is_sellling)
                .build());

        List<Art> artsList = artRepository.findAll();

        Art art = artsList.get(0);
        Assertions.assertThat(art.getArtName()).isEqualTo("lol");
    }
}