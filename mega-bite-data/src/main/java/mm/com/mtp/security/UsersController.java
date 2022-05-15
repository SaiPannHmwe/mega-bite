package mm.com.mtp.security;

/**
 * Created by Sai Pann Hmwe on 6/8/2021.
 */

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UsersController {

    //@PreAuthorize("hasRole('STAFF')")
    @GetMapping("/upload-order-old")
    public String usersStatusCheck(HttpServletRequest request) {
        if (request.isUserInRole("Staff")) {
            return "office/upload-order";
        }
        return "Admin/create-account";
    }

}
