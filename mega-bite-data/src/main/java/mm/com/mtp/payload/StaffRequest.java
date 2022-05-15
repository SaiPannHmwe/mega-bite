package mm.com.mtp.payload;

import lombok.Data;
import mm.com.mtp.type.StaffRole;

import javax.validation.constraints.NotBlank;

/**
 * Created by Sai Pann Hmwe on 1/9/2021.
 */
@Data
public class StaffRequest {

    private Long id;

    private String name;

    @NotBlank(message = "Staff user name must not be empty.")
    private String userName;

    @NotBlank(message = "Phone number must not be empty.")
    private String phoneNumber;

    private StaffRole role;


}
