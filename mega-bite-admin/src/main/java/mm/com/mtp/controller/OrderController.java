package mm.com.mtp.controller;

import mm.com.mtp.datatable.OrderDataTable;
import mm.com.mtp.form.MenuForm;
import mm.com.mtp.security.CurrentUser;
import mm.com.mtp.security.UserPrincipal;
import mm.com.mtp.service.OrderService;
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
@Controller
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/orders")
    public String getAllOrders(@CurrentUser UserPrincipal userPrincipal,
                               Model model) {
        /*if (userPrincipal.getRole().toString().equals("Staff")) {
            model.addAttribute("staff", staffService.getStaffDetail(userPrincipal.getId()));
            return PageUrl.STAFF_CHANGE_PASSWORD;
        }*/
        return PageUrl.ORDER;
    }

    @ResponseBody
    @PostMapping("/orders/dataTable")
    public DataTablesOutput<OrderDataTable> orderDataTable(@RequestBody DataTablesInput dataTablesInput) {
        DataTablesOutput<OrderDataTable> db = orderService.orderDataTable(dataTablesInput);
        return db;
    }

    @GetMapping("/orders/{id}/detail")
    public String getOrderDetail(Model model, @PathVariable("id") Long id) {
        model.addAttribute("order", orderService.getOrder(id));
        return PageUrl.VIEW_ORDER;
    }

    @GetMapping("/orders/{id}/edit")
    public String editOrder(@PathVariable Long id, Model model) {
        OrderDataTable orderForm = orderService.getOrder(id);
        model.addAttribute("orderForm", orderForm);
        //model.addAttribute("isPrevDay", collectForm.getCollectDate().isBefore(LocalDate.now()));
        return PageUrl.NEW_ORDER;
    }

    @PostMapping("/orders/save")
    public String saveOrder(@Valid @ModelAttribute("orderForm") OrderDataTable orderForm,
                              BindingResult result, Model model) {
        //validateFieldErrors(menuService.validateMenuForm(menuForm), result);

        if (result.hasErrors()) {
            return PageUrl.NEW_ORDER;
        }

        orderService.saveOrder(orderForm);
        return "redirect:/orders";
    }

}
