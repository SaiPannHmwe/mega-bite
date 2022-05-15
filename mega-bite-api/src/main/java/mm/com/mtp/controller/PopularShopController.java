package mm.com.mtp.controller;

import mm.com.mtp.payload.ShopResponse;
import mm.com.mtp.service.PopularShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static mm.com.mtp.response.ApiMessage.POPULAR_SHOP_RETRIEVE_SUCCESSFUL;

/**
 * Created by Sai Pann Hmwe on 3/21/2022.
 */
@RestController
@RequestMapping("/api/v1")
public class PopularShopController extends BaseController<ShopResponse> {

    @Autowired
    PopularShopService popularShopService;

    @GetMapping("/popularShop")
    public ResponseEntity<?> getPoplarShop() {
        logRequestData("Get popular Shop.");
        return okResponse(POPULAR_SHOP_RETRIEVE_SUCCESSFUL, popularShopService.getPopularShop());
    }
}
