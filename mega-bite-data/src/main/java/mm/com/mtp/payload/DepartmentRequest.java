package mm.com.mtp.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Created by Sai Pann Hmwe on 1/18/2021.
 */
@Data
public class DepartmentRequest {

    private Long id;

    @NotBlank(message = "Department name must not be blank.")
    private String name;

}
