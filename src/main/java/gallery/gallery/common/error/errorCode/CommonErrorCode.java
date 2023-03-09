package gallery.gallery.common.error.errorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode {
    /**
     * Enum요소에 특정 값을 매핑하고 싶다면, 필드값을 추가하여 매핑시켜주면 됨
     * 여기선 httpStatus, message가 매핑할 특정 값이 되는 것*/

    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "Invalid parameter included"),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "Resource not exists"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"),
            ;

   private final HttpStatus httpStatus;
   private final String message;


}
