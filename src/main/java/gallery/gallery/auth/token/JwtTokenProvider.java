package gallery.gallery.auth.token;

import gallery.gallery.general.dto.TokenDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.InvalidParameterException;
import java.security.Key;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

   private static final String secretKey = System.getenv("SECRET_KEY");
    //30분
    private static final long ACCESS_TOKEN_SEC = 60 * 30;
    //2주
    private static final long REFRESH_TOKEN_SEC = 60 * 60 * 24 * 14;

    private Key getSigninKey(String secretKey) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // 토큰 해석
    public Claims validTokenAndReturnBody(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigninKey(secretKey))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }

        //JWT가 만료 후 수락되거나, 다른 형식으로 JWT를 수신하거나, JWT가 올바르게 구성되지 않을 때
        catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
            e.printStackTrace();
            throw new InvalidParameterException("유효하지 않은 토큰입니다.");
        }
    }


    //유저ID 조회
    public String getName(String token) {
        return validTokenAndReturnBody(token).get("nickname", String.class);
    }

    public String getPassWord(String token) {
        return validTokenAndReturnBody(token).get("password", String.class);
    }

    //토큰 만료
    private Boolean isTokenExpired(String token) {
        Date expiration = validTokenAndReturnBody(token).getExpiration();
        return expiration.before(new Date());
    }

    //access token생성
    public String generateAcessToken(String nickname) {
        return doGenerateToken(nickname, ACCESS_TOKEN_SEC * 1000L);
    }

    //refresh token 생성
    public String generateRefreshToken(String nickname) {
        return doGenerateToken(nickname, REFRESH_TOKEN_SEC * 1000L);
    }

    //accessToken 유효시간 알림(second)
    public Long getValidationAccessTokenTime() {
        return ACCESS_TOKEN_SEC;
    }

    private String doGenerateToken(String nickname, Long expireTime) {
        Claims claims = Jwts.claims();
        claims.put("nickname", nickname);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(getSigninKey(secretKey), SignatureAlgorithm.HS256)
                .compact();
    }


}
