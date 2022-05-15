package mm.com.mtp.payload;

import lombok.Data;
import mm.com.mtp.type.TransactionType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

/**
 * Created by Sai Pann Hmwe on 6/10/2021.
 */
@Data
public class PaymentRequest {

    private Long id;

    private Long orderId;

    private Long paymentChannelId;

    private Integer totalAmount;

    private Integer amount;

    private String description;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    private TransactionType type;

}
