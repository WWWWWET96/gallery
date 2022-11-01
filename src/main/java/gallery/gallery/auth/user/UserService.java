package gallery.gallery.auth.user;

import gallery.gallery.auth.user.UserRepository;
import gallery.gallery.domain.user.entity.User;
import gallery.gallery.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * UserDetail을 통해 사용자 정보를 받고 이 정보를 토대로 사용자를 찾고
 * 만든 UserDetail을 SecurityContextHoder에 제공할 Service
 * UserDetailsService의 loadUserByUsername()의 리턴 타입은 UserDetails임!
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
                .orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 사용자입니다: " + nickname));

    }

    public Long signup(UserDto userDto){
        return userRepository.save(User.of(userDto)).getId();
    }


}
