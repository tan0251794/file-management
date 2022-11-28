package min.project.tms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ApiErrorException extends RuntimeException {
    public ApiErrorException() {
        super();
    }
    public ApiErrorException(String message) {
        super(message);
    }
    public ApiErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
