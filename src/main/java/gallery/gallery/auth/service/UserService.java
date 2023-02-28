package gallery.gallery.auth.service;

import gallery.gallery.auth.repository.UserRepository;
import gallery.gallery.common.error.exception.AlreadyExistedException;
import gallery.gallery.common.error.exception.RestApiException;

import gallery.gallery.general.domain.User;
import gallery.gallery.general.dto.TokenDto;
import gallery.gallery.general.dto.UserDto;
import gallery.gallery.general.dto.requestDto.UserLoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static gallery.gallery.common.error.errorCode.CommonErrorCode.DUPLICATE_RESOURCE;
import static gallery.gallery.common.error.errorCode.CommonErrorCode.RESOURCE_NOT_FOUND;

/**
 * 스프링시큐리티 대신 일반 게시글처럼 저장하고 유효성검사를 따로 해주는 식으로 user, admin 진행하기
 */
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public Long signup(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new AlreadyExistedException(DUPLICATE_RESOURCE);
        }
        String encodedPw = bCryptPasswordEncoder.encode(userDto.getPassword());
        UserDto encodePwUserDto = userDto.toEncodePw(userDto, encodedPw);
        return userRepository.save(User.of(encodePwUserDto)).getId();
    }

    @Transactional
    public TokenDto login(UserLoginDto userLoginDto) {
        String nickName = userLoginDto.getNickname();
        String password = userLoginDto.getPassword();
        //유저 존재여부 확인
        Optional<User> user = userRepository.findByNickname(nickName);
        user.orElseThrow(
                () -> new RestApiException(RESOURCE_NOT_FOUND)
        );
        //비밀번호 확인
        if (!bCryptPasswordEncoder.matches(password, user.get().getPassword())) {
            throw new IllegalArgumentException("Wrong Password");
        }
        TokenDto token = jwtService.createToken(user.get().getNickname());
        return token;

    }
}
