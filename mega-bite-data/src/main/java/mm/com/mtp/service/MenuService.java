package mm.com.mtp.service;

import mm.com.mtp.datatable.MenuDataTable;
import mm.com.mtp.form.MenuForm;
import mm.com.mtp.payload.MenuResponse;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import java.util.List;
import java.util.Map;

/**
 * Created by Sai Pann Hmwe on 4/3/2022.
 */
public interface MenuService {

    DataTablesOutput<MenuDataTable> menuDataTable(DataTablesInput dataTablesInput);

    MenuForm getMenu(Long id);

    List<MenuResponse> getMenu(int start, int size);

    MenuResponse getMenuDetail(Long id);

    void save(MenuForm menuForm);

    Map<Long, String> getMenuMap();

}
