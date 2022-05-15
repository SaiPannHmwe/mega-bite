package mm.com.mtp.controller;

import mm.com.mtp.form.ShopForm;
import mm.com.mtp.model.Cart;
import mm.com.mtp.payload.ShopResponse;
import mm.com.mtp.service.*;
import mm.com.mtp.utils.PageUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Sai Pann Hmwe on 1/20/2021.
 */
@Controller
public class IndexController {

    @Autowired
    OrderService orderService;

    @Autowired
    ShopMenuService shopMenuService;

    @Autowired
    OrderDetailService orderDetailService;

    @Autowired
    MenuService menuService;

    @Autowired
    ShopService shopService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(value = "filter", required = false) String q,
                        HttpSession session) {
        /*if (q != null) {
            //model.addAttribute("order", orderService.getOrder(q));
            model.addAttribute("filter", q);
        }*/
        List<Cart> list = (List<Cart>) session.getAttribute("listCart");
        int count = (list == null) ? 0 : list.stream().mapToInt(Cart::getQuantity).sum();
        model.addAttribute("count", count);

        return PageUrl.INDEX;
    }

    @GetMapping("/shop-cart")
    public String index1(Model model,
                         @RequestParam(value = "filter", required = false) String q) {
        /*if (q != null) {
            //model.addAttribute("order", orderService.getOrder(q));
            model.addAttribute("filter", q);
        }*/
        model.addAttribute("menu", shopMenuService.getShopMenuByShop(1L));
        return PageUrl.SHOP;
    }

    @GetMapping("/shops/{id}/menu")
    public String menu(Model model,
                       @PathVariable Long id,
                       @RequestParam(value = "filter", required = false) String q,
                       HttpSession session) {

        model.addAttribute("shop", shopService.getShop(id));
        model.addAttribute("menu", shopMenuService.getShopMenuByShop(id));

        List<Cart> list = (List<Cart>) session.getAttribute("listCart");
        int count = (list == null) ? 0 : list.stream().mapToInt(Cart::getQuantity).sum();
        model.addAttribute("count", count);

        return PageUrl.MENU;
    }

    @GetMapping("/privacy-policy")
    public String privacyIndex() {
        return PageUrl.NEXT_INDEX;
    }

    @ResponseBody
    @GetMapping("/menuData")
    public List<ShopResponse> getMenu(@RequestParam int start,
                                      @RequestParam String query,
                                      @RequestParam(required = false, defaultValue = "8") int size) {
        List<ShopResponse> db = shopService.getShop(query, start, size);
        return db;

       /* List<MenuResponse> db = menuService.getMenu(start, size);
        return db;*/
    }

    @ResponseBody
    @GetMapping("/popular-shop")
    public ShopForm getPopularShop() {
        ShopForm db = shopService.getPopularShop();
        return db;

       /* List<MenuResponse> db = menuService.getMenu(start, size);
        return db;*/
    }

    @GetMapping("/create-order")
    public String createOrder(Model model) {
        orderDetailService.createOrderDetail(1L, 10L, 1);
        return "redirect:/";
    }

    @GetMapping("/update-order")
    public String updateOrder(Model model) {
        orderDetailService.updateOrderDetail(1L, 10L, 2L, 3);
        return "redirect:/";
    }

    @GetMapping("/remove-order")
    public String deleteOrder(Model model) {
        orderDetailService.deleteOrderDetail(1L);
        return "redirect:/";
    }

}
