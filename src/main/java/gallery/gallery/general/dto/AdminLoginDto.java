package gallery.gallery.general.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AdminLoginDto {
    private String nickname;
    private String password;

    @Builder
    public AdminLoginDto(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }
}
