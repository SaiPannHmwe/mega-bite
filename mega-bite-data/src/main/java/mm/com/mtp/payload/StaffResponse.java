package mm.com.mtp.payload;

import lombok.Data;
import mm.com.mtp.type.StaffRole;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Sai Pann Hmwe on 1/9/2021.
 */
@Data
public class StaffResponse {

    private Long id;

    private String name;

    private String userName;

    private String phoneNumber;

    private StaffRole role;

}
