package gallery.gallery.general.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TokenDto {

    private String accessToken;
    private String refreshToken;
    private Long expireSec; //만료시간(second)

    public TokenDto(String accessToken, String refreshToken, Long expireSec) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expireSec = expireSec;
    }

}
