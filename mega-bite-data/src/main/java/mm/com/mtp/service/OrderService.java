package mm.com.mtp.service;

import mm.com.mtp.datatable.OrderDataTable;
import mm.com.mtp.form.CartForm;
import mm.com.mtp.model.Cart;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import java.util.List;

/**
 * Created by Sai Pann Hmwe on 3/21/2022.
 */
public interface OrderService {

    DataTablesOutput<OrderDataTable> orderDataTable(DataTablesInput dataTablesInput);

    OrderDataTable getOrder(Long id);

    void saveOrder(OrderDataTable orderForm);

    List<Long> getCollectCounts();

    void creatOrder(CartForm cartForm, List<Cart> list);

}
