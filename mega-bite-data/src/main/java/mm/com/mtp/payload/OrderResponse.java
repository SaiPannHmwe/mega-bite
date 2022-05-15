package mm.com.mtp.payload;

import lombok.Data;
import mm.com.mtp.type.OrderStatus;

import java.util.List;

/**
 * Created by Sai Pann Hmwe on 1/6/2021.
 */
@Data
public class OrderResponse {

    private Long id;

    private String orderNumber;

    private String customerId;

    private String customerName;

    private String phoneNumber;

    private String address;

    private OrderStatus status;

    private Integer weight = 0;

    private Integer overWeightFee = 0;

    private Integer totalCharge = 0;

    private Integer totalAmount = 0;

    private Integer deliveryFee = 0;

    private Integer transportationFee = 0;

    private Integer discountAmount = 0;

    private Integer totalItem = 0;

    private TransportationResponse transportation;

    private IssuedOrderDetailResponse issuedList;

    private List<PaymentResponse> payments;

    private List<DestinationResponse> orderDestinations;

    private List<DestinationResponse> orderLocations;

}
