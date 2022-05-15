package mm.com.mtp.security;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import mm.com.mtp.model.Staff;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

import static mm.com.mtp.security.SecurityConstants.ACCESS_TOKEN_EXPIRATION_TIME;
import static mm.com.mtp.security.SecurityConstants.SECRET;

@Slf4j
@Component
public class JwtTokenProvider {

    public String generateToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + (1000 * ACCESS_TOKEN_EXPIRATION_TIME));

        return Jwts.builder().setSubject(userPrincipal.getId().toString()).setIssuedAt(now).setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();
    }

    public String getUserIdFromJwt(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }

    public String refreshToken(Staff staff) {
        UserPrincipal userPrincipal = UserPrincipal.create(staff);

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + (1000 * ACCESS_TOKEN_EXPIRATION_TIME));

        return Jwts.builder().setSubject(userPrincipal.getId().toString()).setIssuedAt(now).setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();
    }


}
