package mm.com.mtp.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import mm.com.mtp.utils.ApiConstants;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Created by Sai Pann Hmwe on 1/6/2021.
 */
@Data
@Builder
public class ApiError<T> {

    private HttpStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ApiConstants.DATETIME_FORMAT)
    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();

    private String message;

    private T error;

}
