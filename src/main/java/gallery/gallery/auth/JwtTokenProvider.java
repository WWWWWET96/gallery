package gallery.gallery.auth;


import gallery.gallery.common.Enum.AccountStatus;
import gallery.gallery.domain.user.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.PrivateKey;
import java.util.Base64;
import java.util.Date;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Component
public class JwtTokenProvider  {

    //서명 인코딩에 사용되는 비밀키
    private static final String key = "secretKeyasdasejhksahekfajhekfnskaaskejbfkabq";

    private static byte[] keyBytes = Decoders.BASE64.decode(key);

    private static final Key secretKey = Keys.hmacShaKeyFor(keyBytes);

    //토큰 유효시간 30분
    private static long tokenValidTime = 30 * 60 * 1000L;
    private final UserDetailsService userDetailsService;

    //jwt 토큰 생성
    public static String createToken(String userPk, AccountStatus accountStatus){
        //JWT payload에 저장되는 정보단위. user를 식별하는 값을 넣음
        Claims claims = Jwts.claims().setSubject(userPk);
        claims.put("MEMBER", accountStatus);
        //토큰의 시작시작 만료시간 설정
        Date iat = new Date();
        Date exp = new Date(iat.getTime() + tokenValidTime);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(iat)
                .setExpiration(exp)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }
    //Request의 Header에서 token값을 가져오기
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    //토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwtToken);
            return !claimsJws.getBody().getExpiration().before(new Date());
        }catch (Exception e){
            return false;
        }
    }

    //JWT토큰에서 인증 정보 취득
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    //토큰에서 회원 정보 추출
    private String getUserPk(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
    }
}
