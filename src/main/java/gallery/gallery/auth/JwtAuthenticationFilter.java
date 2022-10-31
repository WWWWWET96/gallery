package gallery.gallery.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * JwtAuthenticationFilter : 검증을 끝낸 JWT로부터 유저 정보를 조회해와서
 * UserPasswordAuthenticationFilter로 전달
*/
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends GenericFilterBean {
    //토큰을 검증하기위한 provider
    private final JwtTokenProvider jwtTokenProvider;
    //로그인에대한 필터링을 진행
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //헤더에서 JWT를 받아온다.
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
        //유효한 토큰인지 확인한다.
        if(token != null && jwtTokenProvider.validateToken(token)){
            //토큰이 유효하면 토큰으로부터 유저 정보를 받아온다.
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            //SecurityContext에 Authentication객체를 저장한다.
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }
}
