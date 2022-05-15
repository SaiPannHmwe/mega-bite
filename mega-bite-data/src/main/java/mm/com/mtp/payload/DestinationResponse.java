package mm.com.mtp.payload;

import lombok.Data;

import java.time.LocalDate;

/**
 * Created by Sai Pann Hmwe on 1/14/2021.
 */
@Data
public class DestinationResponse {

    private Long id;

    private String name;

    private LocalDate date;

}
