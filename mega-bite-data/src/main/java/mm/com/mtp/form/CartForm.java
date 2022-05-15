package mm.com.mtp.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Created by Sai Pann Hmwe on 5/10/2022.
 */
@Data
public class CartForm {

    @NotBlank(message = "User name must not be blank.")
    private String name;

    @NotBlank(message = "Phone number must not be blank.")
    private String phoneNumber;

    @NotBlank(message = "Address must not be blank.")
    private String address;


}
