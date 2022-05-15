package mm.com.mtp.payload;

import lombok.Data;
import mm.com.mtp.type.OrderStatus;
import mm.com.mtp.type.TransactionType;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * Created by Sai Pann Hmwe on 6/10/2021.
 */
@Data
public class PaymentResponse {

    private Long id;

    private Long orderId;

    private String orderNumber;

    private String paymentChannelName;

    private String customerName;

    private String phoneNumber;

    private String address;

    private Integer weight = 0;

    private Integer totalCharge = 0;

    private Integer totalAmount = 0;

    private Integer deliveryFee = 0;

    private Integer transportationFee = 0;

    private Integer amount;

    private Integer payableReceivableAmount;

    private String description;

    private OrderStatus status;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    private TransactionType type;

    private ImageResponse image;

}
