package mm.com.mtp.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Created by Sai Pann Hmwe on 6/10/2021.
 */
@Data
public class PaymentChannelRequest {

    private Long id;

    @NotBlank(message = "Payment channel name must not be blank.")
    private String name;

    private String number;

}
