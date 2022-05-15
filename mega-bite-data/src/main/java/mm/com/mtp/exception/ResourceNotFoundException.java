package mm.com.mtp.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Getter
public class ResourceNotFoundException extends RuntimeException {

    private String resourceName;
    private String fieldName;
    private String otherName;
    private Object fieldValue;
    private Object otherValue;

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : %s", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public ResourceNotFoundException(String resourceName, String fieldName, String otherName, Object fieldValue, Object otherValue) {
        super(String.format("%s not found with <%s,%s> : <%s,%s>", resourceName, fieldName, otherName, fieldValue, otherValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.otherName = otherName;
        this.fieldValue = fieldValue;
        this.otherValue = otherValue;
    }
}
