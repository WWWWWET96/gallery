package gallery.gallery.auth.service;

import gallery.gallery.auth.token.JwtTokenProvider;
import gallery.gallery.general.dto.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtTokenProvider jwtTokenProvider;

    //토큰 발급
    public TokenDto createToken(String nickname){
        String accessToken = jwtTokenProvider.generateAcessToken(nickname);
        String refreshToken = jwtTokenProvider.generateRefreshToken(nickname);
        return new TokenDto(accessToken, refreshToken,
                jwtTokenProvider.getValidationAccessTokenTime());
    }

    //refresh 토큰 갱신
    public TokenDto createTokenByRefreshToken(String refreshToken){
        String key = jwtTokenProvider.getName(refreshToken);
        String accessToken = jwtTokenProvider.generateAcessToken(key);
        String refreshTokenNew = jwtTokenProvider.generateRefreshToken(key);
        return new TokenDto(accessToken, refreshTokenNew,
                jwtTokenProvider.getValidationAccessTokenTime());
    }
}
