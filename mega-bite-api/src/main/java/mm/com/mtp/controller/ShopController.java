package mm.com.mtp.controller;

import mm.com.mtp.payload.ShopRequest;
import mm.com.mtp.payload.ShopResponse;
import mm.com.mtp.response.ApiResponse;
import mm.com.mtp.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static mm.com.mtp.response.ApiMessage.*;

/**
 * Created by Sai Pann Hmwe on 4/2/2022.
 */
@Controller
@RequestMapping("/api/v1")
public class ShopController extends BaseController<ShopResponse> {

    @Autowired
    ShopService shopService;

    @GetMapping("/shops")
    public ResponseEntity<?> getPoplarShop() {
        logRequestData("Get All Shop List.");
        return okResponse(SHOP_RETRIEVE_SUCCESSFUL, shopService.getShop());
    }

    @GetMapping("/shops/{id}")
    public ResponseEntity<?> getPoplarShop(@PathVariable Long id) {
        logRequestData("Get Shop Detail.");
        return okResponse(SHOP_RETRIEVE_SUCCESSFUL, shopService.getShopDetail(id));
    }

    /**
     * Shop Registration
     */
    @PostMapping("/registerShop")
    public ResponseEntity<ApiResponse<ShopResponse>> registerShop(@RequestBody @Valid ShopRequest shopRequest) {
        logRequestData("Register shop with < " + shopRequest + " > .");

        ShopResponse shopResponse = shopService.registerShop(shopRequest);

        if (shopResponse == null) {
            return internalServerErrorResponse(STAFF_REGISTER_ERROR);
        }
        return createdResponse(SHOP_REGISTER_SUCCESSFUL, shopResponse);
    }

    /**
     * Download Image
     */
    @GetMapping("/shop/{id}/images")
    public ResponseEntity<Resource> downloadImage(@PathVariable Long id, HttpServletRequest request) {
        logRequestData("Get image for shop Id <" + id + ">");
        return resourceResponse(request, shopService.getImage(id));
    }

}
