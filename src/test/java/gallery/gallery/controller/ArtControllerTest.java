package gallery.gallery.controller;

import gallery.gallery.service.ArtService;
import gallery.gallery.common.enums.Selling;
import gallery.gallery.dto.ArtDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.assertj.core.api.Assertions;

import java.time.LocalDateTime;

@SpringBootTest
@AutoConfigureMockMvc
public class ArtControllerTest {
    @MockBean
    ArtService artService;
    @Autowired
    MockMvc mockMvc;

    @Test
    void save() throws Exception{
        ArtDto artDto =  ArtDto.builder()
                .id(1L)
                .author("kim")
                .art_name("lol")
                .reg_date(LocalDateTime.now())
                .closed_date(LocalDateTime.now())
                .is_selling(Selling.BEFORE)
                .build();
        ArtDto response = artService.saveArt(artDto);
        System.out.println(response.getAuthor());
        Assertions.assertThat(response.getArt_name()).isEqualTo("lol");


    }


}