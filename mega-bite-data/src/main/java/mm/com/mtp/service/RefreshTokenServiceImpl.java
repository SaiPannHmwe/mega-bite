package mm.com.mtp.service;

import mm.com.mtp.exception.ResourceNotFoundException;
import mm.com.mtp.model.RefreshToken;
import mm.com.mtp.repository.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Sai Pann Hmwe on 10/11/2021.
 */
@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Override
    public RefreshToken getRefreshToken(String token) {
        return refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new ResourceNotFoundException("Token invalid."));
    }

}
