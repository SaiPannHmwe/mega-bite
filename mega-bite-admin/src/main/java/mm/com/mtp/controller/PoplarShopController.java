package mm.com.mtp.controller;

import lombok.extern.slf4j.Slf4j;
import mm.com.mtp.form.PopularShopForm;
import mm.com.mtp.form.ShopForm;
import mm.com.mtp.service.ShopService;
import mm.com.mtp.utils.PageUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * Created by Sai Pann Hmwe on 4/12/2022.
 */
@Slf4j
@Controller
public class PoplarShopController {

    @Autowired
    ShopService shopService;

    @ResponseBody
    @GetMapping("/shop/shopMap")
    public Map<Long, String> getShopMap() {
        return shopService.getShopMap();
    }

    @GetMapping("/popularShop/new")
    public String newCollect(@ModelAttribute("shopForm") PopularShopForm shopForm, Model model) {
        return PageUrl.NEW_POPULAR_SHOP;
    }

    @PostMapping("/popularShop/save")
    public String savePopularShop(@Valid @ModelAttribute("shopForm") PopularShopForm shopForm,
                                  BindingResult result, Model model) {
        if (result.hasErrors()) {
            return PageUrl.NEW_POPULAR_SHOP;
        }
        shopService.savePopularShop(shopForm);
        return "redirect:/dashboard";
    }

    @GetMapping("/popularShop/{id}/edit")
    public String editCollect(@PathVariable Long id, Model model) {
        ShopForm shopForm = shopService.getShop(id);
        model.addAttribute("shopForm", shopForm);
        return PageUrl.NEW_POPULAR_SHOP;
    }

}
