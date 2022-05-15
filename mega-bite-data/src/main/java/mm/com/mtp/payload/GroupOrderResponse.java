package mm.com.mtp.payload;

import lombok.Data;

import java.util.List;

/**
 * Created by Sai Pann Hmwe on 7/4/2021.
 */
@Data
public class GroupOrderResponse {

    private Long groupId;

    private OrderResponse parentOrder;

    private List<OrderResponse> memberOrders;

}
