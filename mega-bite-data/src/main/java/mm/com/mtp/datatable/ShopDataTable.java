package mm.com.mtp.datatable;

import lombok.Data;

/**
 * Created by Sai Pann Hmwe on 4/3/2022.
 */
@Data
public class ShopDataTable {

    private Long id;

    private String name;

    private String address;

    private String phoneNumber;

    private String image;

    private boolean isClosed;

    private boolean isResolved;

}
