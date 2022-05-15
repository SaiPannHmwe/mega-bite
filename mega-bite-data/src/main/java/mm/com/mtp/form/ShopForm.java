package mm.com.mtp.form;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

/**
 * Created by Sai Pann Hmwe on 4/3/2022.
 */
@Data
public class ShopForm {

    private Long id;

    @NotBlank(message = "Shop name must not be blank.")
    private String name;

    @NotBlank(message = "Address must not be blank.")
    private String address;

    @NotBlank(message = "Phone Number must not be blank.")
    private String phoneNumber;

    private MultipartFile image;

    private String url;

    private Boolean isNewImage = false;

}
