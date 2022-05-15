package mm.com.mtp.mapper;

import javax.annotation.Generated;
import mm.com.mtp.datatable.OrderDetailDataTable;
import mm.com.mtp.model.Menu;
import mm.com.mtp.model.Order;
import mm.com.mtp.model.OrderDetail;
import mm.com.mtp.model.Shop;
import mm.com.mtp.model.ShopMenu;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-11T20:44:16+0630",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_261 (Oracle Corporation)"
)
@Component
public class OrderDetailMapperImpl implements OrderDetailMapper {

    @Override
    public OrderDetailDataTable toOrderDetailDataTable(OrderDetail orderDetail) {
        if ( orderDetail == null ) {
            return null;
        }

        OrderDetailDataTable orderDetailDataTable = new OrderDetailDataTable();

        orderDetailDataTable.setName( orderDetailShopMenuMenuName( orderDetail ) );
        orderDetailDataTable.setShopName( orderDetailShopMenuShopName( orderDetail ) );
        orderDetailDataTable.setPhoneNumber( orderDetailShopMenuShopPhoneNumber( orderDetail ) );
        orderDetailDataTable.setTotalCount( orderDetail.getQuantity() );
        orderDetailDataTable.setId( orderDetail.getId() );
        orderDetailDataTable.setTotalAmount( orderDetail.getTotalAmount() );

        return orderDetailDataTable;
    }

    @Override
    public OrderDetail toOrderDetail(Order order, ShopMenu shopMenu, int quantity) {
        if ( order == null && shopMenu == null ) {
            return null;
        }

        OrderDetail orderDetail = new OrderDetail();

        if ( order != null ) {
            orderDetail.setTotalAmount( order.getTotalAmount() );
        }
        if ( shopMenu != null ) {
            orderDetail.setShopMenu( shopMenu );
        }
        orderDetail.setQuantity( quantity );

        return orderDetail;
    }

    @Override
    public OrderDetail toOrderDetail(Order order, ShopMenu shopMenu, OrderDetail orderDetail, int quantity) {
        if ( order == null && shopMenu == null && orderDetail == null ) {
            return null;
        }

        OrderDetail orderDetail1 = new OrderDetail();

        if ( orderDetail != null ) {
            orderDetail1.setCreatedDate( orderDetail.getCreatedDate() );
            orderDetail1.setId( orderDetail.getId() );
            orderDetail1.setOrder( orderDetail.getOrder() );
            orderDetail1.setShopMenu( orderDetail.getShopMenu() );
        }
        orderDetail1.setQuantity( quantity );

        return orderDetail1;
    }

    private String orderDetailShopMenuMenuName(OrderDetail orderDetail) {
        if ( orderDetail == null ) {
            return null;
        }
        ShopMenu shopMenu = orderDetail.getShopMenu();
        if ( shopMenu == null ) {
            return null;
        }
        Menu menu = shopMenu.getMenu();
        if ( menu == null ) {
            return null;
        }
        String name = menu.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String orderDetailShopMenuShopName(OrderDetail orderDetail) {
        if ( orderDetail == null ) {
            return null;
        }
        ShopMenu shopMenu = orderDetail.getShopMenu();
        if ( shopMenu == null ) {
            return null;
        }
        Shop shop = shopMenu.getShop();
        if ( shop == null ) {
            return null;
        }
        String name = shop.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String orderDetailShopMenuShopPhoneNumber(OrderDetail orderDetail) {
        if ( orderDetail == null ) {
            return null;
        }
        ShopMenu shopMenu = orderDetail.getShopMenu();
        if ( shopMenu == null ) {
            return null;
        }
        Shop shop = shopMenu.getShop();
        if ( shop == null ) {
            return null;
        }
        String phoneNumber = shop.getPhoneNumber();
        if ( phoneNumber == null ) {
            return null;
        }
        return phoneNumber;
    }
}
