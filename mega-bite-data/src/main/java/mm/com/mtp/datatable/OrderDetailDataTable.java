package mm.com.mtp.datatable;

import lombok.Data;

/**
 * Created by Sai Pann Hmwe on 4/10/2022.
 */
@Data
public class OrderDetailDataTable {

    private Long id;

    private String name;

    private String shopName;

    private String phoneNumber;

    private Integer totalCount = 0;

    private Integer totalAmount = 0;

}
