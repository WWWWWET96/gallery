package gallery.gallery.common.error.errorCode;

import org.springframework.http.HttpStatus;

public interface ErrorCode {

    /**
     * 클라이언트에게 보내줄 에러 코드를 정의
     * HTTP 상태 및 메시지
     *
     *
     * */
    HttpStatus getHttpStatus();
    String getMessage();
}
