package mm.com.mtp.payload;

import lombok.Data;

/**
 * Created by Sai Pann Hmwe on 3/21/2022.
 */
@Data
public class ShopResponse {

    private Long id;

    private String name;

    private String address;

    private String phoneNumber;

    private boolean isClosed;

    private boolean isMostPopular;

    private String url;

}
