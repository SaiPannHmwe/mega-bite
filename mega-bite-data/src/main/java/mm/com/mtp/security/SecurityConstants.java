package mm.com.mtp.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SecurityConstants {

    public static Long REFRESH_TOKEN_EXPIRATION_TIME;

    public static Long ACCESS_TOKEN_EXPIRATION_TIME;

    public static final String SECRET = "MTPSecretKey";
    public static final long EXPIRATION_TIME = 604800000; // 7 days
    public static final String HEADER_NAME = "Authorization";
    public static final String TOKEN_TYPE = "Bearer ";

    @Value("${access-token-expiration-time}")
    public void setAccessTokenExpirationTime(Long expirationTime) {
        ACCESS_TOKEN_EXPIRATION_TIME = expirationTime;
        System.out.println("Access token expiration time : " + expirationTime);
    }

    @Value("${refresh-token-expiration-time}")
    public void setRefreshTokenExpirationTime(Long expirationTime) {
        REFRESH_TOKEN_EXPIRATION_TIME = expirationTime;
        System.out.println("Refresh token expiration time : " + expirationTime);
    }

}