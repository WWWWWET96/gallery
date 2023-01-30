package gallery.gallery.common.error.errorResponse;

import gallery.gallery.common.error.errorCode.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Builder
@Getter
public class ErrorResponse {
    /**
     * 유저에게 보낼 응답 포맷
     * */
    private final ErrorCode errorCode;
    private final String message;

    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode errorCode){
        return  ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ErrorResponse.builder()
                        .errorCode(errorCode)
                        .message(errorCode.getMessage())
                        .build());
    }
}



