package mm.com.mtp.service;

import lombok.extern.slf4j.Slf4j;
import mm.com.mtp.model.Staff;
import mm.com.mtp.repository.StaffRepository;
import mm.com.mtp.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomAgentDetailsService implements UserDetailsService {

    @Autowired
    StaffRepository staffRepository;

    // This method is used by Spring security.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername username<" + username + ">");

        Staff staff = staffRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Staff does not exist."));
        return UserPrincipal.create(staff);
    }

    // This method is used by JwtAuthenticationFilter.
    public UserDetails loadUserById(Long id) {
        //log.info("loadUserById id<" + id + ">");

        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Staff does not exist."));
        return UserPrincipal.create(staff);
    }

}
