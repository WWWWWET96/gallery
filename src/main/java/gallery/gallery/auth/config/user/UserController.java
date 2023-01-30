package gallery.gallery.auth.config.user;

import gallery.gallery.dto.UserDto;
import gallery.gallery.dto.requestDto.UserLoginDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final JwtUserTokenProvider jwtUserTokenProvider;

    @GetMapping()
    public ResponseEntity<String> index(){
        return ResponseEntity.ok("200");
    }

    /**
     * @RequestBody : 사용자로부터 가져올 객체
     * @Valid : @RequestBody 객체를 사용자로부터 가져올 때, 들어오는 값들을 검증
     *        : service단이 아닌 객체 안에서 들어오는 값에 대한 검증을 할 수 있다.
     * */
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody UserLoginDto userLoginDto){
        UserDto ourUser = userService.login(userLoginDto.getNickname());
        String token = jwtUserTokenProvider.createToken(ourUser.getNickname(), ourUser.getAccountStatus());
        return ResponseEntity.ok(token);
    }

    @PostMapping
    public ResponseEntity<Long> signUp(@Valid @RequestBody UserDto userDto){
        Long response = userService.signup(userDto);
        return ResponseEntity.ok(response);
    }


}
