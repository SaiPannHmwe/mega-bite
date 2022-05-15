package mm.com.mtp.controller;

import mm.com.mtp.datatable.MenuDataTable;
import mm.com.mtp.form.MenuForm;
import mm.com.mtp.payload.MenuResponse;
import mm.com.mtp.security.CurrentUser;
import mm.com.mtp.security.UserPrincipal;
import mm.com.mtp.service.MenuService;
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
import java.util.List;
import java.util.Map;

/**
 * Created by Sai Pann Hmwe on 4/3/2022.
 */
@Controller
public class MenuController {

    @Autowired
    MenuService menuService;

    @GetMapping("/menu")
    public String getAllMenu(@CurrentUser UserPrincipal userPrincipal,
                             Model model) {
        /*if (userPrincipal.getRole().toString().equals("Staff")) {
            model.addAttribute("staff", staffService.getStaffDetail(userPrincipal.getId()));
            return PageUrl.STAFF_CHANGE_PASSWORD;
        }*/
        return PageUrl.MENU;
    }

    @ResponseBody
    @PostMapping("/menu/dataTable")
    public DataTablesOutput<MenuDataTable> menuDataTable(@RequestBody DataTablesInput dataTablesInput) {
        return menuService.menuDataTable(dataTablesInput);
    }



    @GetMapping("/menu/{id}/detail")
    public String getMenuDetail(Model model, @PathVariable("id") Long id) {
        model.addAttribute("menu", menuService.getMenuDetail(id));
        return PageUrl.VIEW_MENU;
    }

    @GetMapping("/menu/new")
    public String newMenu(@ModelAttribute("menuForm") MenuForm menuForm, Model model) {
        model.addAttribute("isToday", true);
        return PageUrl.NEW_MENU;
    }

    @PostMapping("/menu/save")
    public String saveMenu(@Valid @ModelAttribute("menuForm") MenuForm menuForm,
                              BindingResult result, Model model) {
        //validateFieldErrors(menuService.validateMenuForm(menuForm), result);

        if (result.hasErrors()) {
            return PageUrl.NEW_MENU;
        }

        menuService.save(menuForm);
        return "redirect:/menu";
    }

    @GetMapping("/menu/{id}/edit")
    public String editMenu(@PathVariable Long id, Model model) {
        MenuForm menuForm = menuService.getMenu(id);
        model.addAttribute("menuForm", menuForm);
        //model.addAttribute("isPrevDay", collectForm.getCollectDate().isBefore(LocalDate.now()));
        return PageUrl.NEW_MENU;
    }

    @ResponseBody
    @GetMapping("/menu/menuMap")
    public Map<Long, String> getShopMap() {
        return menuService.getMenuMap();
    }


}
