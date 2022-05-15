package mm.com.mtp.payload;

import lombok.Data;

/**
 * Created by Sai Pann Hmwe on 1/6/2021.
 */
@Data
public class OrderRequest {

    private Long id;

    private String customerId;

    private String customerName;

    private String phoneNumber;

    private String address;

    private String orderNumber;

    private Integer weight;

    private Integer deliveryFee;

    private Integer totalAmount;

    private Integer transportationFee;

    private Integer discountAmount;

}
