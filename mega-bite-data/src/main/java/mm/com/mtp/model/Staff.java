package mm.com.mtp.model;

import lombok.Data;
import mm.com.mtp.type.StaffRole;

import javax.persistence.*;

/**
 * Created by Sai Pann Hmwe on 3/21/2022.
 */
@Entity
@Data
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"phoneNumber"})})
public class Staff extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String userName;

    private String phoneNumber;

    private String password;

    @Enumerated(value = EnumType.STRING)
    private StaffRole role;

}
