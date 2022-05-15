package mm.com.mtp.mapper;

import mm.com.mtp.datatable.OrderDetailDataTable;
import mm.com.mtp.model.Order;
import mm.com.mtp.model.OrderDetail;
import mm.com.mtp.model.ShopMenu;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * Created by Sai Pann Hmwe on 4/10/2022.
 */
@Mapper(componentModel = "spring")
public interface OrderDetailMapper {

    @Mappings({
            @Mapping(target = "name", source = "orderDetail.shopMenu.menu.name"),
            @Mapping(target = "shopName", source = "orderDetail.shopMenu.shop.name"),
            @Mapping(target = "phoneNumber", source = "orderDetail.shopMenu.shop.phoneNumber"),
            @Mapping(target = "totalCount", source = "orderDetail.quantity")
    })
    OrderDetailDataTable toOrderDetailDataTable(OrderDetail orderDetail);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "lastModifiedDate", ignore = true),
            //@Mapping(target = "menu", source = "shopMenu")
    })
    OrderDetail toOrderDetail(Order order, ShopMenu shopMenu, int quantity);

    @Mappings({
            @Mapping(target = "id", source = "orderDetail.id"),
            @Mapping(target = "createdDate", source = "orderDetail.createdDate"),
            @Mapping(target = "lastModifiedDate", ignore = true),
            @Mapping(target = "totalAmount", ignore = true),
            @Mapping(target = "quantity", source = "quantity"),
            //@Mapping(target = "menu", source = "shopMenu")
    })
    OrderDetail toOrderDetail(Order order, ShopMenu shopMenu, OrderDetail orderDetail, int quantity);

}
