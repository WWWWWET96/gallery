package gallery.gallery.auth.token;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.*;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends GenericFilterBean {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //헤더에서 로드
        HttpServletRequest requestServlet = (HttpServletRequest) request;
        String authorization = requestServlet.getHeader("Authorization");

        //유효한지 확인
        if (authorization != null) {
            String accessToken = authorization.replace("Bearer", "");
            String nickname = jwtTokenProvider.getName(accessToken);
            String password = jwtTokenProvider.getPassWord(accessToken);

            //nickname이 나오지 않는다면 잘못된 토큰이 만들어진거기대문에 에러 리턴
            if (nickname.isEmpty()) {
                throw new InvalidParameterException("유효하지 않은 토큰입니다.");
            }
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    nickname, password, getAuthorities()
            );

            //접근 허가
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }

    private Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_USER"));

        return grantedAuthorityList;
    }


}
