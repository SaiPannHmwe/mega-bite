package mm.com.mtp.repository;

import mm.com.mtp.model.RefreshToken;
import mm.com.mtp.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Sai Pann Hmwe on 10/10/2021.
 */
@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByStaff(Staff staff);

    Optional<RefreshToken> findByToken(String token);

}
