package gallery.gallery.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserLoginDto {

    private String nickname;
    private String password;

    @Builder
    public UserLoginDto(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }
}
