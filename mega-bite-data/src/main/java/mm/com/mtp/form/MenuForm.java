package mm.com.mtp.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Created by Sai Pann Hmwe on 4/3/2022.
 */
@Data
public class MenuForm {

    private Long id;

    @NotBlank(message = "Menu name must not be blank.")
    private String name;

    @NotBlank(message = "Menu name must not be blank.")
    private String nameChinese;

}
