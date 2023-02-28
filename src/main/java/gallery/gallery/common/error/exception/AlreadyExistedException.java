package gallery.gallery.common.error.exception;

import gallery.gallery.common.error.errorCode.CommonErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class AlreadyExistedException extends RuntimeException {
    private final CommonErrorCode commonErrorCode;
}
