//package gallery.gallery.auth.user;
//
//import gallery.gallery.auth.token.JwtTokenProvider;
//import io.jsonwebtoken.Claims;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@ExtendWith(SpringExtension.class)
//class JwtTokenProviderTest {
//
//
//    @Test
//    void tokenTest(){
//        //given
//        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
//
//        //when
//        String nickname = "sconelee";
//        String accessToken = jwtTokenProvider.generateAcessToken(nickname);
//
//        Claims claims = jwtTokenProvider.validTokenAndReturnBody(accessToken);
//        System.out.println("claims = "+ claims);
//        String findNickname = claims.get("nickname", String.class);
//
//        //then
//        assertThat(nickname).isEqualTo(jwtTokenProvider.getName(accessToken));
//        assertThat(nickname).isEqualTo(findNickname);
//
//    }
//
//}