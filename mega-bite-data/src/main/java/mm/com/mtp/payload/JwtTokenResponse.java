package mm.com.mtp.payload;

import lombok.Data;
import lombok.NoArgsConstructor;
import mm.com.mtp.security.SecurityConstants;

/**
 * Created by Sai Pann Hmwe on 1/24/2021.
 */
@Data
@NoArgsConstructor
public class JwtTokenResponse {

    private final String tokenType = "Bearer";

    private String accessToken;

    private String refreshToken;

    private Long expiresIn;

    public JwtTokenResponse(String accessToken) {
        this.accessToken = accessToken;
        this.expiresIn = SecurityConstants.ACCESS_TOKEN_EXPIRATION_TIME;
    }

}
