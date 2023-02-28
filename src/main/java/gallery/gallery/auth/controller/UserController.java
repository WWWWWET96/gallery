package gallery.gallery.auth.controller;

import gallery.gallery.auth.service.UserService;

import gallery.gallery.general.dto.TokenDto;
import gallery.gallery.general.dto.UserDto;
import gallery.gallery.general.dto.requestDto.UserLoginDto;
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
    @GetMapping()
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("200");
    }

    /**
     * @RequestBody : 사용자로부터 가져올 객체
     * @Valid : @RequestBody 객체를 사용자로부터 가져올 때, 들어오는 값들을 검증
     * : service단이 아닌 객체 안에서 들어오는 값에 대한 검증을 할 수 있다.
     */
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@Valid @RequestBody UserLoginDto userLoginDto) {
        TokenDto response = userService.login(userLoginDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Long> signUp(@Valid @RequestBody UserDto userDto) {
        Long response = userService.signup(userDto);
        return ResponseEntity.ok(response);
    }


}
