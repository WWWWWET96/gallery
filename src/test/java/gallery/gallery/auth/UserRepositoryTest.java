package gallery.gallery.auth;

import gallery.gallery.common.Enum.AccountStatus;
import gallery.gallery.domain.user.entity.User;
import gallery.gallery.web.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void SaveUserTest(){
        //given
        UserDto userDto = UserDto.builder()
                .nickname("abc")
                .name("lee")
                .password("abc")
                .phone("010-1111-1111")
                .email("abc@abc.com")
                .accountStatus(AccountStatus.WAITING)
                .build();
        User savedUser = userRepository.save(User.of(userDto));

        /**
         * UserEntity.of(userDto) ->converting, mapping
            엔티티 안에서 마감기한 validation진행할 수 있음
            매핑과정에서 위의 validation이용해서 dto->entity할 때 검증 가능
         */
        //when
        User findUser = userRepository.findById(savedUser.getId()).get();
        //then
        assertThat(findUser.getNickname()).isEqualTo("abc");


    }

}