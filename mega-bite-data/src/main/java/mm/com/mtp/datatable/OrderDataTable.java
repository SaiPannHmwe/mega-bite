package mm.com.mtp.datatable;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

/**
 * Created by Sai Pann Hmwe on 6/27/2021.
 */
@Data
public class OrderDataTable {

    private Long id;

    private LocalDate date;

    private String shopName;

    private String shopPhone;

    private String shopAddress;

    @NotBlank(message = "Customer name must not be blank.")
    private String customerName;

    @NotBlank(message = "Customer Phone must not be blank.")
    private String customerPhone;

    @NotBlank(message = "Customer Address must not be blank.")
    private String customerAddress;

    private Integer totalCount = 0;

    private Integer totalAmount = 0;

    //private OrderStatus status;

    //private Integer weight;

    //private Integer deliveryFee;

    //private Integer transportationFee;

    //private Integer discountAmount;

    //List<DestinationResponse> orderDestinations;

}
