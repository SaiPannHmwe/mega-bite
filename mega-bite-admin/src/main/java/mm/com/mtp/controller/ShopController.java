package mm.com.mtp.controller;

import lombok.extern.slf4j.Slf4j;
import mm.com.mtp.datatable.ShopDataTable;
import mm.com.mtp.form.ShopForm;
import mm.com.mtp.security.CurrentUser;
import mm.com.mtp.security.UserPrincipal;
import mm.com.mtp.service.ShopService;
import mm.com.mtp.utils.PageUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Sai Pann Hmwe on 4/3/2022.
 */
@Slf4j
@Controller
public class ShopController {

    @Autowired
    ShopService shopService;

    @GetMapping("/shops")
    public String getAllShop(@CurrentUser UserPrincipal userPrincipal,
                             Model model) {
        /*if (userPrincipal.getRole().toString().equals("Staff")) {
            model.addAttribute("staff", staffService.getStaffDetail(userPrincipal.getId()));
            return PageUrl.STAFF_CHANGE_PASSWORD;
        }*/
        return PageUrl.SHOP;
    }

    @GetMapping("/shops/{id}/detail")
    public String getShopDetail(Model model, @PathVariable("id") Long id) {
        model.addAttribute("shop", shopService.getShop(id));
        return PageUrl.VIEW_SHOP;
    }

    @ResponseBody
    @PostMapping("/shops/dataTable")
    public DataTablesOutput<ShopDataTable> callLogDataTable(@RequestBody DataTablesInput dataTablesInput) {
        DataTablesOutput<ShopDataTable> db = shopService.shopDataTable(dataTablesInput);
        return db;
    }

    @GetMapping("/shops/new")
    public String newCollect(@ModelAttribute("shopForm") ShopForm shopForm, Model model) {
        return PageUrl.NEW_SHOP;
    }

    @PostMapping("/shops/save")
    public String saveCollect(@Valid @ModelAttribute("shopForm") ShopForm shopForm,
                              BindingResult result, Model model) {
        if (result.hasErrors()) {
            return PageUrl.NEW_SHOP;
        }
        shopService.save(shopForm);
        return "redirect:/shops";
    }

    @GetMapping("/shops/{id}/edit")
    public String editCollect(@PathVariable Long id, Model model) {
        ShopForm shopForm = shopService.getShop(id);
        model.addAttribute("shopForm", shopForm);
        return PageUrl.NEW_SHOP;
    }

    @GetMapping("/shops/{shopId}/makePopular")
    public String makePopular(@PathVariable Long shopId) {
        shopService.makePopular(shopId);
        return "redirect:/shops";
    }


}
