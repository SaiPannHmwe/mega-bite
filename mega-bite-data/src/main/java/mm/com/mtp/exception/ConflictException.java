package mm.com.mtp.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
@Getter
public class ConflictException extends RuntimeException {

    private String resourceName;
    private String fieldName;
    private Object fieldValue;

    public ConflictException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s already exist with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public ConflictException(String reason) {
        super(reason);
    }

}
