package mm.com.mtp.controller;

import mm.com.mtp.datatable.ShopMenuDataTable;
import mm.com.mtp.form.CartForm;
import mm.com.mtp.mapper.CartMapper;
import mm.com.mtp.model.Cart;
import mm.com.mtp.service.OrderService;
import mm.com.mtp.service.ShopMenuService;
import mm.com.mtp.utils.PageUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sai Pann Hmwe on 5/8/2022.
 */
@Controller
public class CartController {

    @Autowired
    private ShopMenuService shopMenuService;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private OrderService orderService;

    @GetMapping("/cart")
    public String cart(Model model,
                       HttpSession session) {

        List<Cart> list = (List<Cart>) session.getAttribute("cart");
        model.addAttribute("listCart", list);

        Integer total = 0;
        if (list != null) {
            for (Cart c : list) {
                total += c.getPrice();
            }
        }
        model.addAttribute("total", total);

        int count = (list == null) ? 0 : list.stream().mapToInt(Cart::getQuantity).sum();
        model.addAttribute("count", count);

        return PageUrl.SHOP;
    }

    @GetMapping("/menu/{id}/add")
    public String index(Model model,
                        @PathVariable Long id,
                        HttpSession session) {
        ShopMenuDataTable menu = shopMenuService.getShopMenuDataTable(id);
        Cart cart = new Cart();
        List<Cart> list = (List<Cart>) session.getAttribute("cart");
        if (list == null) {
            list = new ArrayList<Cart>();
        }

        if (menu != null) {
            cart.toCart(menu);
            Integer total = addToCart(list, cart);
            model.addAttribute("total", total);
            session.setAttribute("cart", list);
        }
        return "redirect:/cart";
    }

    @GetMapping("/menu/{id}/remove")
    public String indexRemove(Model model,
                              @PathVariable Long id,
                              HttpSession session) {

        List<Cart> list = (List<Cart>) session.getAttribute("cart");
        if (list != null) {
            Integer total = removeCartItem(list, id);
            model.addAttribute("total", total);
            session.setAttribute("cart", list);
        }
        return "redirect:/cart";
    }

    @GetMapping("/menu/{id}/addQuantity")
    public String indexAddQuantity(@PathVariable Long id,
                                   HttpSession session) {

        List<Cart> list = (List<Cart>) session.getAttribute("cart");

        for (Cart c : list) {
            if (c.getId().equals(id)) {
                c.setPrice(c.getPrice() / c.getQuantity());
                c.setQuantity(c.getQuantity() + 1);
                c.setPrice(c.getPrice() * c.getQuantity());
            }
        }
        return "redirect:/cart";
    }

    @GetMapping("/menu/{id}/removeQuantity")
    public String indexRemoveQuantity(@PathVariable Long id,
                                      HttpSession session) {

        List<Cart> list = (List<Cart>) session.getAttribute("cart");

        for (Cart c : list) {
            if (c.getId().equals(id)) {
                c.setPrice(c.getPrice() / c.getQuantity());
                c.setQuantity(c.getQuantity() - 1);
                c.setPrice(c.getPrice() * c.getQuantity());
            }
        }
        return "redirect:/cart";
    }

    private Integer removeCartItem(List<Cart> list, Long id) {
        Integer total = 0;
        Cart temp = null;

        for (Cart c : list) {
            if (c.getId().equals(id)) {
                temp = c;
                continue;
            }
            total += c.getPrice() * c.getQuantity();
        }

        if (temp != null) {
            list.remove(temp);
        }
        return total;
    }

    public Integer addToCart(List<Cart> list, Cart cart) {
        Integer total = 0;
        boolean isExist = false;

        for (Cart c : list) {
            if (c.getId().equals(cart.getId())) {
                c.setQuantity(c.getQuantity() + 1);
                c.setPrice(cart.getPrice() * c.getQuantity());
                isExist = true;
            }
            total += c.getPrice();
        }

        if (!isExist) {
            list.add(cart);
            total += cart.getPrice() * cart.getQuantity();
        }
        return total;
    }

    @PostMapping("/menu/add/cart")
    public String indexAddCart(@Valid @ModelAttribute("cartForm") CartForm cartForm,
                               HttpSession session) {
        List<Cart> list = (List<Cart>) session.getAttribute("cart");
        orderService.creatOrder(cartForm, list);
        session.invalidate();
        return "redirect:/";
    }

}
