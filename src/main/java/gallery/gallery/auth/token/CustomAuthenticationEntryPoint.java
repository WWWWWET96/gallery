package gallery.gallery.auth.token;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

//토큰 유효성 실패 시, Exception 결과를 정해진 form으로 리턴
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    /**ObjectMapper
     * JSON컨텐츠를 Java객체로 deserialization하거나 Java객체를 JSON으로 serialization할 때
     * 사용하는 Jackson 라이브러리의 클래스*/
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        try (OutputStream os = response.getOutputStream()) {
            HashMap fail = new HashMap();
            fail.put("errMsg", "유효하지 않은 토큰입니다.");
            fail.put("errCd", "ERROR");
            objectMapper.writeValue(os, fail);
            os.flush();
        }
    }
}
