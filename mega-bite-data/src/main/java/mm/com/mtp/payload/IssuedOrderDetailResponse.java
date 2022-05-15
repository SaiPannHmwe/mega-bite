package mm.com.mtp.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Sai Pann Hmwe on 12/22/2020.
 */
@Data
@NoArgsConstructor
public class IssuedOrderDetailResponse {

    private Long orderId;

    private String customerId;

    private String customerName;

    private Integer issuedCount = 0;

    List<IssuedOrderResponse> issuedOrders;

}
