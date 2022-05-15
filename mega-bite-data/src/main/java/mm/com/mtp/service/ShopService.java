package mm.com.mtp.service;

import mm.com.mtp.datatable.ShopDataTable;
import mm.com.mtp.form.PopularShopForm;
import mm.com.mtp.form.ShopForm;
import mm.com.mtp.payload.ShopRequest;
import mm.com.mtp.payload.ShopResponse;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import java.util.List;
import java.util.Map;

/**
 * Created by Sai Pann Hmwe on 4/2/2022.
 */
public interface ShopService {

    ShopForm getShop(Long id);

    ShopResponse getShopDetail(Long id);

    List<ShopResponse> getShop();

    List<ShopResponse> getShop(String query, int start, int size);

    ShopResponse registerShop(ShopRequest shopRequest);

    DataTablesOutput<ShopDataTable> shopDataTable(DataTablesInput dataTablesInput);

    void save(ShopForm shopForm);

    void savePopularShop(PopularShopForm shopForm);

    Map<Long, String> getShopMap();

    Resource getImage(Long shopId);

    void makePopular(Long shopId);

    ShopForm getPopularShop();

}
