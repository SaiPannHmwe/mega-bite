package mm.com.mtp.datatable;

import lombok.Data;
import mm.com.mtp.payload.ImageResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Sai Pann Hmwe on 7/9/2021.
 */
@Data
public class TransportationDataTable {

    private Long id;

    private String order;

    private String customer;

    private String staff;

    private Integer transportationFee;

    private String fromGate;

    private String toGate;

    private Boolean isConfirmed;

    private ImageResponse image;

}
