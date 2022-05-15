package mm.com.mtp.repository;

import mm.com.mtp.model.Staff;
import mm.com.mtp.type.StaffRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Sai Pann Hmwe on 1/9/2021.
 */
@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

    boolean existsByPhoneNumber(String phoneNumber);

    Optional<Staff> findByName(String name);

    Optional<Staff> findByUserName(String name);

    List<Staff> findAllByRole(StaffRole role);

    List<Staff> findAllByRoleIn(List<StaffRole> roles);

    boolean existsByUserNameIgnoreCase(String username);

    boolean existsByPhoneNumberIgnoreCase(String phoneNumber);

    boolean existsByUserNameAndIdNot(String userName, Long id);

    boolean existsByPhoneNumberAndIdNot(String phoneNumber, Long id);
}
