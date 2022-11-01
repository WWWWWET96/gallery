package gallery.gallery.auth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Configuration : Spring Security를 활성화
 * WebSecurityConfig : Spring Security의 설정 파일
 * */
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

   private final JwtUserTokenProvider jwtUserTokenProvider;

    /**
     * Spring Security 5.7.x부터 WebSecurityConfigurerAdapter는
     * 컴포넌트 기반의 보안 설정을 권장한다는 이유로 Deprecated.
     * ->SecurityFilterChain, WebSecurityCustomizer를 상황에 따라 빈으로 등록하여 사용
     */
   @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

       http.csrf().disable(); //jwt토큰이용하니까 csrf protection을 사용할 필요가 없음
       http.httpBasic().disable()
               .authorizeRequests()//요청에 대한 사용권한 체크
               .antMatchers("/**").permitAll()
               .antMatchers("/users/**").permitAll()
               .antMatchers("/admins/**").hasRole("ADMIN")
               .and()
               .addFilterBefore(new JwtAuthenticationFilter(jwtUserTokenProvider),
                       UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
