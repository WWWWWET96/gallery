package gallery.gallery.auth.config.user;


import gallery.gallery.auth.config.admin.AdminService;
import gallery.gallery.common.enums.AccountStatus;
import gallery.gallery.domain.Admin;
import gallery.gallery.domain.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Date;


@Slf4j
@RequiredArgsConstructor
@Component
public class JwtUserTokenProvider {

    //서명 인코딩에 사용되는 비밀키
    private static final String key = "secretKeyUsersejhksahekfajhekfnskaaskejbfkabq";

    private static byte[] keyBytes = Decoders.BASE64.decode(key);

    private static final Key secretKey = Keys.hmacShaKeyFor(keyBytes);

    //토큰 유효시간 30분
    private static long tokenValidTime = 30 * 60 * 1000L;
    private final UserService userService;
    private final AdminService adminService;

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
    public Authentication getUserAuthentication(String token) {
        User user = userService.loadUserByUsername(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(user, "", user.getAuthorities());
    }

    //JWT토큰에서 인증 정보 취득
    public Authentication getAdminAuthentication(String token) {
        Admin admin = adminService.loadUserByUsername(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(admin, "", admin.getAuthorities());
    }
    //토큰에서 회원 정보 추출
    private String getUserPk(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
    }
}
