package mm.com.mtp.payload;

import lombok.Data;
import lombok.NoArgsConstructor;
import mm.com.mtp.type.IssueType;

/**
 * Created by Sai Pann Hmwe on 1/31/2021.
 */
@Data
@NoArgsConstructor
public class IssuedOrderRequest {

    private IssueType type;

    private String description;

}
