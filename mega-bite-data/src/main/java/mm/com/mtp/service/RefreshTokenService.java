package mm.com.mtp.service;

import mm.com.mtp.model.RefreshToken;

/**
 * Created by Sai Pann Hmwe on 10/11/2021.
 */
public interface RefreshTokenService {

    RefreshToken getRefreshToken(String token);

}
