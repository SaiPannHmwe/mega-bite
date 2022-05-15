package mm.com.mtp.datatable;

import lombok.Data;
import mm.com.mtp.payload.ImageResponse;
import mm.com.mtp.type.OrderStatus;
import mm.com.mtp.type.TransactionType;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * Created by Sai Pann Hmwe on 9/11/2021.
 */
@Data
public class PaymentDataTable {

    private Long id;

    private Long orderId;

    private String orderNumber;

    private String paymentChannel;

    private String name;

    private Integer amount;

    private Integer payableReceivableAmount;

    private String description;

    private OrderStatus status;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    private TransactionType type;

    private ImageResponse image;

    private Boolean isApproved;

}
