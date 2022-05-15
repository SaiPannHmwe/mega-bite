package mm.com.mtp.mapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import mm.com.mtp.datatable.OrderDataTable;
import mm.com.mtp.form.CartForm;
import mm.com.mtp.model.Order;
import mm.com.mtp.model.OrderDetail;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-11T20:44:16+0630",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_261 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Order toOrder(CartForm cartForm, LocalDate date) {
        if ( cartForm == null && date == null ) {
            return null;
        }

        Order order = new Order();

        if ( cartForm != null ) {
            order.setCustomerAddress( cartForm.getAddress() );
            order.setCustomerPhone( cartForm.getPhoneNumber() );
            order.setCustomerName( cartForm.getName() );
        }
        if ( date != null ) {
            order.setDate( date );
        }

        return order;
    }

    @Override
    public OrderDataTable toOrderDataTable(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDataTable orderDataTable = new OrderDataTable();

        orderDataTable.setId( order.getId() );
        orderDataTable.setDate( order.getDate() );
        orderDataTable.setCustomerName( order.getCustomerName() );
        orderDataTable.setCustomerPhone( order.getCustomerPhone() );
        orderDataTable.setCustomerAddress( order.getCustomerAddress() );
        orderDataTable.setTotalCount( order.getTotalCount() );
        orderDataTable.setTotalAmount( order.getTotalAmount() );

        return orderDataTable;
    }

    @Override
    public Order toOrder(Order order, OrderDataTable orderForm) {
        if ( order == null && orderForm == null ) {
            return null;
        }

        Order order1 = new Order();

        if ( order != null ) {
            order1.setDate( order.getDate() );
            order1.setTotalAmount( order.getTotalAmount() );
            order1.setId( order.getId() );
            order1.setTotalCount( order.getTotalCount() );
            order1.setCreatedDate( order.getCreatedDate() );
            order1.setLastModifiedDate( order.getLastModifiedDate() );
            order1.setShop( order.getShop() );
            List<OrderDetail> list = order.getOrderDetails();
            if ( list != null ) {
                order1.setOrderDetails( new ArrayList<OrderDetail>( list ) );
            }
        }
        if ( orderForm != null ) {
            order1.setCustomerAddress( orderForm.getCustomerAddress() );
            order1.setCustomerPhone( orderForm.getCustomerPhone() );
            order1.setCustomerName( orderForm.getCustomerName() );
        }

        return order1;
    }
}
