package gallery.gallery.repository;

import gallery.gallery.common.enums.Selling;
import gallery.gallery.domain.Art;
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
        String art_name = "lol";
        LocalDateTime reg_date = LocalDateTime.now();
        LocalDateTime closed_date = LocalDateTime.now();
        Selling is_sellling = Selling.BEFORE;

        artRepository.save(Art.builder()
                .author(author)
                .art_name(art_name)
                .reg_date(reg_date)
                .closed_date(closed_date)
                .isSelling(is_sellling)
                .build());

        List<Art> artsList = artRepository.findAll();

        Art art = artsList.get(0);
        Assertions.assertThat(art.getArt_name()).isEqualTo("lol");
    }
}