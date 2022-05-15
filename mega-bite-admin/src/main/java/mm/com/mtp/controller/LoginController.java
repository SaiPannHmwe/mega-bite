package mm.com.mtp.controller;

import lombok.extern.slf4j.Slf4j;
import mm.com.mtp.security.CurrentUser;
import mm.com.mtp.security.UserPrincipal;
import mm.com.mtp.utils.PageUrl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * Created by Sai Pann Hmwe on 1/24/2021.
 */
@Slf4j
@Controller
public class LoginController {

    @GetMapping("/login")
    String login() {
        return PageUrl.LOGIN;
    }

    /*@GetMapping({"/", "/create-login"})
    public String usersStatusCheck(@CurrentUser UserPrincipal userPrincipal,
                                   Model model) {
        if (userPrincipal.getRole().toString().equals("Staff")) {
            return "redirect:/upload-order";
        } else if (userPrincipal.getRole().toString().equals("Admin")) {
            return "redirect:/create-account";
        }
        return "redirect:/login";
    }*/

}
