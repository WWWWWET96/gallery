package gallery.gallery.auth.config.admin;

import gallery.gallery.web.dto.AdminDto;
import gallery.gallery.web.dto.AdminLoginDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admins")
@Slf4j
public class AdminController {
    private final AdminService adminService;
    private final JwtAdminTokenProvider jwtAdminTokenProvider;
    @GetMapping()
    public ResponseEntity<String> index(){
        return ResponseEntity.ok("200");
    }

    @PostMapping("/signup")
    public ResponseEntity<Long> signup(@Valid@RequestBody AdminDto adminDto){
        Long response = adminService.signup(adminDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody AdminLoginDto adminLoginDto){
        AdminDto ourAdmin = adminService.login(adminLoginDto.getNickname());
        String token = jwtAdminTokenProvider.createToken(ourAdmin.getNickname(), ourAdmin.getAdmin_role());
        return ResponseEntity.ok(token);
    }
}
