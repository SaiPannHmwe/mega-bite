package mm.com.mtp.payload;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Sai Pann Hmwe on 1/31/2021.
 */
@Data
public class TransportationRequest {

    private String fromGate;

    private String toGate;

    private Integer transportationFee;

}
