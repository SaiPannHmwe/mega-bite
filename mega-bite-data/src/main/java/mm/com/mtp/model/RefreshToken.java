package mm.com.mtp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import mm.com.mtp.security.SecurityConstants;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by Sai Pann Hmwe on 10/10/2021.
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class RefreshToken extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @OneToOne(targetEntity = Staff.class, fetch = FetchType.LAZY)
    //@JoinColumn(nullable = true, name = "staff_id")
    private Staff staff;

    private LocalDateTime expiryDateTime;

    public RefreshToken(Staff staff) {
        this.staff = staff;
        this.token = UUID.randomUUID().toString();
        this.expiryDateTime = LocalDateTime.now().plusMinutes(SecurityConstants.REFRESH_TOKEN_EXPIRATION_TIME);
    }

}