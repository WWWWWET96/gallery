package gallery.gallery.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import gallery.gallery.auth.user.UserController;
import gallery.gallery.auth.user.UserRepository;
import gallery.gallery.auth.user.UserService;
import gallery.gallery.common.Enum.AccountStatus;
import gallery.gallery.web.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
   private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UserService userService;
    @MockBean
    private UserRepository userRepository;

    @Test
    void 회원가입test() throws Exception {
        //given
        UserDto userDto = UserDto.builder()
                .nickname("aaa")
                .name("kim")
                .password("aaa")
                .phone("010-2222-2222")
                .email("aaa@aaa.com")
                .accountStatus(AccountStatus.MEMBER)
                .build();

        String request = objectMapper.writeValueAsString(userDto);
        //when
        ResultActions resultActions = mockMvc.perform(
                post("/users/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request)
        );

        //then
        resultActions.andExpect(status().isOk());
    }


}