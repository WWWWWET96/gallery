package gallery.gallery.common.error;

import gallery.gallery.common.error.errorResponse.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Runtime에러면 너무 방대함 -> http status 종류를 다 지원하지 못함
 */

//@RestControllerAdvice
//public class ErrorHandler {
//    @ExceptionHandler(BusinessException.class)
//    public ResponseEntity<String> handleBusinessException(BusinessException e){
//        String message = e.getMessage();
//
//        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
//    }
//    @ExceptionHandler(EntityNotFoundException.class)
//    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException e){
//        String message = e.getMessage();
//
//        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
//    }
//}
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler{
    /**
     * @RestControllerAdvice: 전역적으로 에러를 처리해주는 어노테이션(프로젝트에서 발생하는 모든 예외를 잡아줌)
     * 스프링은 예외를 미리 처리해둔 ResponseEntityExceptionHandler를 추상 클래스로 제공하고 있음
     * ResponseEntityExceptionHandler에는 스프링 예외에 대한 ExceptionHandler가 모두 구현되어 있기에 이를 상속받으면 됨
     * 하지만 에러메시지는 반환하지 않으니까 에러 응답을 보내기 위한 메소드 오버라이딩 필요
     * @ExceptionHandler: 발생한 특정 예외를 잡아서 하나의 메소드에서 공통 처리해줄 수 있도록
     */

    @ExceptionHandler(RestApiException.class)
    public ResponseEntity<ErrorResponse> handleRestApiException(RestApiException e){
        log.error("handleCustomException throw CustomException : ", e.getCommonErrorCode());

      return ErrorResponse.toResponseEntity(e.getCommonErrorCode());
    }
}

