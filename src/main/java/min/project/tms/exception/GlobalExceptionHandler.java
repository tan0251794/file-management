package min.project.tms.exception;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class    GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<String> errors = new ArrayList<String>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        ErrorDetails errorDetails =
                new ErrorDetails(HttpStatus.BAD_REQUEST, "Validation Error", errors);
        return handleExceptionInternal(
                ex, errorDetails, headers, errorDetails.getStatus(), request);
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), "Handle All");
        return new ResponseEntity<Object>(
                errorDetails, new HttpHeaders(), errorDetails.getStatus());
    }

    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, WebRequest request) {
        String error =
                ex.getName() + " should be of type " + ex.getRequiredType().getName();

        ErrorDetails errorDetails =
                new ErrorDetails(HttpStatus.BAD_REQUEST, "Type Mismatch Error", error);
        return new ResponseEntity<Object>(
                errorDetails, new HttpHeaders(), errorDetails.getStatus());
    }

    @ExceptionHandler(ApiErrorException.class)
    public ResponseEntity<?> apiErrorException(ApiErrorException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), "error occurred");
        return new ResponseEntity<Object>(
                errorDetails, new HttpHeaders(), errorDetails.getStatus());
    }

}
