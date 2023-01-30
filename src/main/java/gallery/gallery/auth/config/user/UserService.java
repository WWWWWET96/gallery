package gallery.gallery.auth.config.user;

import gallery.gallery.common.error.RestApiException;
import gallery.gallery.common.error.errorCode.CommonErrorCode;
import gallery.gallery.domain.User;
import gallery.gallery.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * UserDetail을 통해 사용자 정보를 받고 이 정보를 토대로 사용자를 찾고
 * 만든 UserDetail을 SecurityContextHoder에 제공할 Service
 * UserDetailsService의 loadUserByUsername()의 리턴 타입은 UserDetails임!
 * */

/**
 * 토큰으로 현재 login중인 user를 알아내서 이 정보를 계속 사용해야 할거같은데.. ex) jsp에서 model에 사용자 정보 담아둬서 사용했던것처럼
 * 이 user를 알아내서 이 user가 어떤 art를 wishlist에 넣었는지 이 연관관계를 정의내릴 수 있는거아닌가
 * */
@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    // 반환값 : User의 nickName
    public UserDto login(String nickname){
        User user = loadUserByUsername(nickname);
        System.out.println("login success");
        return UserDto.of(user);
    }
    @Override
    public User loadUserByUsername(String nickname) throws UsernameNotFoundException {
       return userRepository.findByNickname(nickname)
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));

    }
    public Long signup(UserDto userDto){
        return userRepository.save(User.of(userDto)).getId();
    }


}
