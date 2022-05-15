package mm.com.mtp.controller;

import mm.com.mtp.utils.PageUrl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Sai Pann Hmwe on 5/7/2022.
 */
@Controller
public class ShopController {

    @GetMapping("/shop")
    public String index(Model model,
                        @RequestParam(value = "filter", required = false) String query) {
        model.addAttribute("filter", query);
        /*if (q != null) {
            //model.addAttribute("order", orderService.getOrder(q));
            model.addAttribute("filter", q);
        }*/
        return PageUrl.INDEX;
    }

}
