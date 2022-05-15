package mm.com.mtp.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import mm.com.mtp.model.Staff;
import mm.com.mtp.type.StaffRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author Nay Myo Htet
 * @since Sep 17, 2019
 */
@AllArgsConstructor
@Getter
public class UserPrincipal implements UserDetails {

    private Long id;
    private String name;
    private String password;
    private StaffRole role;
    //private String department;
    private Collection<? extends GrantedAuthority> authorities;

    public static UserPrincipal create(Staff staff) {
        List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(staff.getRole().toString()));
        //authorities.add(new SimpleGrantedAuthority(staff.getDepartment().getName()));
        return new UserPrincipal(staff.getId(), staff.getName(), staff.getPassword(), staff.getRole(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
