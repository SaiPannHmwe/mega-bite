package mm.com.mtp.payload;

import lombok.Data;
import mm.com.mtp.type.IssueType;

import java.time.LocalDate;

/**
 * Created by Sai Pann Hmwe on 1/31/2021.
 */
@Data
public class IssuedOrderResponse {

    private Long id;

    private IssueType type;

    private String description;

    private LocalDate date;

}
