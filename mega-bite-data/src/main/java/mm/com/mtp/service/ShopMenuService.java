package mm.com.mtp.service;

import mm.com.mtp.datatable.ShopMenuDataTable;
import mm.com.mtp.form.ShopMenuForm;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import java.util.List;
import java.util.Map;

/**
 * Created by Sai Pann Hmwe on 4/6/2022.
 */
public interface ShopMenuService {

    ShopMenuForm createShopMenu(ShopMenuForm shopMenuForm);

    ShopMenuForm getShopMenu(Long id);

    ShopMenuDataTable getShopMenuDataTable(Long id);

    List<ShopMenuForm> getShopMenu();

    DataTablesOutput<ShopMenuDataTable> shopMenuDataTable(DataTablesInput dataTablesInput, Map<String, Object> filterConditions);

    Resource getImage(Long id);

    List<ShopMenuDataTable> getShopMenuByShop(Long shopId);

}
