package singla.jai.studentproject.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex,WebRequest request){
    ExceptionResponse exceptionResponse=new ExceptionResponse(new Date(),HttpStatus.NOT_FOUND.toString(),ex.getMessage(),request.getDescription(false));
    return new ResponseEntity<>(exceptionResponse,HttpStatus.NOT_FOUND);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    logger.info("MethodArgumentNotValid");
    String errorMessage = "Errors:";
    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
      errorMessage =
          errorMessage + "  " + (error.getField() + ": " + error.getDefaultMessage()) + ".";
    }

    logger.info(ex.getMessage());
    ExceptionResponse exceptionResponse =
        new ExceptionResponse(
            new Date(),
            HttpStatus.BAD_REQUEST.toString(),
            errorMessage,
            request.getDescription(false));
    return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);
   }
  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
                                                                    WebRequest request) {
    ExceptionResponse exceptionResponse=new ExceptionResponse(new Date(),HttpStatus.BAD_REQUEST.toString(),ex.getMessage(),request.getDescription(false) );
    return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);
  }
  @ExceptionHandler({ Exception.class })
  public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
    ExceptionResponse exceptionResponse=new ExceptionResponse(new Date(),HttpStatus.BAD_REQUEST.toString(),ex.getMessage(),request.getDescription(false) );
    logger.info("General Exception Encountered");
    return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);
  }
}
