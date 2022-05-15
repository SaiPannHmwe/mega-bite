package mm.com.mtp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Sai Pann Hmwe on 1/24/2021.
 */
@Data
@AllArgsConstructor
public class StaffLoginResponse {

    private JwtTokenResponse token;

    private StaffResponse profile;

}
