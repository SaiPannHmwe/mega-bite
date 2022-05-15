package mm.com.mtp.payload;

import lombok.Data;

/**
 * Created by Sai Pann Hmwe on 1/31/2021.
 */
@Data
public class TransportationResponse {

    private Long id;

    private String orderNumber;

    private String customer;

    private String staff;

    private Integer transportationFee;

    private String fromGate;

    private String toGate;

    private Boolean isConfirmed;

    private ImageResponse image;

}
