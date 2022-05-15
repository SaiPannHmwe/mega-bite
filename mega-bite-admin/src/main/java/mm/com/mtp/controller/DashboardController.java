package mm.com.mtp.controller;

import lombok.extern.slf4j.Slf4j;
import mm.com.mtp.security.CurrentUser;
import mm.com.mtp.security.UserPrincipal;
import mm.com.mtp.service.OrderService;
import mm.com.mtp.service.ShopService;
import mm.com.mtp.utils.PageUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Sai Pann Hmwe on 4/11/2022.
 */
@Slf4j
@Controller
public class DashboardController {

    @Autowired
    OrderService orderService;

    @Autowired
    ShopService shopService;

    @GetMapping({"/", "/dashboard"})
    public String getAllOrders(@CurrentUser UserPrincipal userPrincipal,
                               Model model) {
        model.addAttribute("shop", shopService.getPopularShop());
        model.addAttribute("megaCounts", orderService.getCollectCounts());
        /*if (userPrincipal.getRole().toString().equals("Staff")) {
            model.addAttribute("staff", staffService.getStaffDetail(userPrincipal.getId()));
            return PageUrl.STAFF_CHANGE_PASSWORD;
        }*/
        return PageUrl.DASHBOARD;
    }

}
