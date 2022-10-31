package gallery.gallery.auth;

import gallery.gallery.web.dto.UserDto;
import gallery.gallery.web.dto.UserLoginDto;
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
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/")
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
        String token = jwtTokenProvider.createToken(ourUser.getNickname(), ourUser.getAccountStatus());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/signup")
    public ResponseEntity<Long> signUp(@Valid @RequestBody UserDto userDto){
        Long response = userService.save(userDto);
        return ResponseEntity.ok(response);
    }



}
