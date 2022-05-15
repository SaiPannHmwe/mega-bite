package mm.com.mtp.controller;

import lombok.extern.slf4j.Slf4j;
import mm.com.mtp.datatable.ShopMenuDataTable;
import mm.com.mtp.form.ShopMenuForm;
import mm.com.mtp.security.CurrentUser;
import mm.com.mtp.security.UserPrincipal;
import mm.com.mtp.service.ShopMenuService;
import mm.com.mtp.utils.PageUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sai Pann Hmwe on 4/6/2022.
 */
@Slf4j
@Controller
public class ShopMenuController {

    @Autowired
    ShopMenuService shopMenuService;

    @GetMapping("/shopMenu")
    public String getAllShopMenu(@CurrentUser UserPrincipal userPrincipal,
                                 Model model) {
        /*if (userPrincipal.getRole().toString().equals("Staff")) {
            model.addAttribute("staff", staffService.getStaffDetail(userPrincipal.getId()));
            return PageUrl.STAFF_CHANGE_PASSWORD;
        }*/
        return PageUrl.SHOP_MENU;
    }

    @ResponseBody
    @PostMapping("/shopMenu/dataTable")
    public DataTablesOutput<ShopMenuDataTable> shopMenuDataTable(@RequestBody DataTablesInput dataTablesInput,
                                                                 @RequestParam Long shopId) {
        Map<String, Object> filterConditions = new HashMap<>();
        if (shopId > 0) filterConditions.put("shopId", shopId);
        DataTablesOutput<ShopMenuDataTable> db = shopMenuService.shopMenuDataTable(dataTablesInput, filterConditions);
        return db;
    }


    @GetMapping("shops/{shopId}/shopMenu")//shops/{id}/shopMenu
    public String createShopMenu(@PathVariable Long shopId,
                                 @ModelAttribute("shopMenuForm") ShopMenuForm shopMenuForm,
                                 Model model) {
        shopMenuForm.setShopId(shopId);
        model.addAttribute("shopMenuForm", shopMenuForm);
        return PageUrl.NEW_SHOP_MENU;
    }

    @PostMapping("/shopMenu/save")
    public String saveShopMenu(HttpServletRequest request,
                               @Valid @ModelAttribute("shopMenuForm") ShopMenuForm shopMenuForm,
                               BindingResult result,
                               Model model) {

        if (shopMenuForm.getImage().isEmpty() && shopMenuForm.getUrl().isEmpty()) {
            model.addAttribute("checkFile", "Please choose Image!");
            model.addAttribute("shopMenuForm", shopMenuForm);
            return PageUrl.NEW_SHOP_MENU;
        }
        shopMenuService.createShopMenu(shopMenuForm);

        //String pathTrace = request.getHeader("referer");
        //System.out.println("Request is comming from : " + pathTrace);

        return PageUrl.NEW_SHOP_MENU;
    }

    @GetMapping("/advertisements/{id}/detail")
    public String detailShopMenu(@PathVariable("id") Long id, Model model) {
        model.addAttribute("shopMenuForm", shopMenuService.getShopMenu(id));
        return PageUrl.VIEW_SHOP_MENU;
    }

    @GetMapping("/advertisements/{id}/edit")
    public String editShopMenu(@PathVariable("id") Long id, Model model) {
        model.addAttribute("shopMenuForm", shopMenuService.getShopMenu(id));
        return PageUrl.NEW_SHOP_MENU;
    }

}
