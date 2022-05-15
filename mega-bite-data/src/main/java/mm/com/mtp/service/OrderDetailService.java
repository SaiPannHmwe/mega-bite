package mm.com.mtp.service;

import mm.com.mtp.datatable.OrderDetailDataTable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import java.util.Map;

/**
 * Created by Sai Pann Hmwe on 4/10/2022.
 */
public interface OrderDetailService {

    DataTablesOutput<OrderDetailDataTable> orderDetailDataTable(DataTablesInput dataTablesInput, Map<String, Object> filterConditions);

    void createOrderDetail(Long orderId, Long menuId, int quantity);

    void updateOrderDetail(Long orderId, Long menuId, Long orderDetailId, int quantity);

    void deleteOrderDetail(Long id);

}
