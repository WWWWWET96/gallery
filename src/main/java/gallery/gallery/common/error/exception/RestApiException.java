package gallery.gallery.common.error.exception;

import gallery.gallery.common.error.errorCode.CommonErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RestApiException extends RuntimeException {
    private final CommonErrorCode commonErrorCode;
}
