package mm.com.mtp.mapper;

import mm.com.mtp.datatable.OrderDataTable;
import mm.com.mtp.form.CartForm;
import mm.com.mtp.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.time.LocalDate;

/**
 * Created by Sai Pann Hmwe on 4/10/2022.
 */
@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "date", source = "date"),
            @Mapping(target = "totalCount", ignore = true),
            @Mapping(target = "totalAmount", ignore = true),
            @Mapping(target = "customerName", source = "cartForm.name"),
            @Mapping(target = "customerPhone", source = "cartForm.phoneNumber"),
            @Mapping(target = "customerAddress", source = "cartForm.address")
    })
    Order toOrder(CartForm cartForm, LocalDate date);


    /* @Mappings({
             @Mapping(target = "id", source = "order.id"),
             @Mapping(target = "shopName", source = "order.shop.name"),
             @Mapping(target = "shopPhone", source = "order.shop.phoneNumber"),
             @Mapping(target = "shopAddress", source = "order.shop.address")
     })*/
    OrderDataTable toOrderDataTable(Order order);

    @Mappings({
            @Mapping(target = "id", source = "order.id"),
            @Mapping(target = "date", source = "order.date"),
            @Mapping(target = "totalCount", source = "order.totalCount"),
            @Mapping(target = "totalAmount", source = "order.totalAmount"),
            @Mapping(target = "customerName", source = "orderForm.customerName"),
            @Mapping(target = "customerPhone", source = "orderForm.customerPhone"),
            @Mapping(target = "customerAddress", source = "orderForm.customerAddress")
    })
    Order toOrder(Order order, OrderDataTable orderForm);

}
