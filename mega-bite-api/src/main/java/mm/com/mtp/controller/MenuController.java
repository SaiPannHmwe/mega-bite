package mm.com.mtp.controller;

import mm.com.mtp.payload.MenuRequest;
import mm.com.mtp.payload.MenuResponse;
import mm.com.mtp.payload.ShopResponse;
import mm.com.mtp.response.ApiResponse;
import mm.com.mtp.service.MenuService;
import mm.com.mtp.service.ShopMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static mm.com.mtp.response.ApiMessage.SHOP_REGISTER_SUCCESSFUL;
import static mm.com.mtp.response.ApiMessage.STAFF_REGISTER_ERROR;

/**
 * Created by Sai Pann Hmwe on 4/3/2022.
 */
@Controller
@RequestMapping("/api/v1")
public class MenuController extends BaseController {

    @Autowired
    MenuService menuService;

    @Autowired
    ShopMenuService shopMenuService;

    /**
     * Shop Registration
     */
    /*@PostMapping("/registerShop")
    public ResponseEntity<ApiResponse<ShopResponse>> registerShop(@RequestBody @Valid MenuRequest menuRequest) {
        logRequestData("Create menu with < " + menuRequest + " > .");

        MenuResponse menuResponse = menuService.createMenu(menuRequest);

        if (menuResponse == null) {
            return internalServerErrorResponse(STAFF_REGISTER_ERROR);
        }
        return createdResponse(SHOP_REGISTER_SUCCESSFUL, menuResponse);
    }*/

    /**
     * Download Image
     */
    @GetMapping("/shopMenu/{id}/images")
    public ResponseEntity<Resource> downloadImage(@PathVariable Long id, HttpServletRequest request) {
        logRequestData("Get image for shop menu Id <" + id + ">");
        return resourceResponse(request, shopMenuService.getImage(id));
    }

}
