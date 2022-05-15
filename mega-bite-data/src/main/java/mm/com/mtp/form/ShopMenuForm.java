package mm.com.mtp.form;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * Created by Sai Pann Hmwe on 4/6/2022.
 */
@Data
public class ShopMenuForm {

    private Long id;

    private Long shopId;

    private Long menuId;

    private Integer price = 0;

    @NotNull(message = "Menu image must not be empty.")
    private MultipartFile image;

    private String url;

    private Boolean isNewImage = false;

}
