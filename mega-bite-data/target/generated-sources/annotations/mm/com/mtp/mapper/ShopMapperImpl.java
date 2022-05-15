package mm.com.mtp.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import mm.com.mtp.datatable.ShopDataTable;
import mm.com.mtp.form.PopularShopForm;
import mm.com.mtp.form.ShopForm;
import mm.com.mtp.model.Shop;
import mm.com.mtp.model.ShopImage;
import mm.com.mtp.model.ShopMenu;
import mm.com.mtp.payload.ShopRequest;
import mm.com.mtp.payload.ShopResponse;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-11T20:44:15+0630",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_261 (Oracle Corporation)"
)
@Component
public class ShopMapperImpl implements ShopMapper {

    @Override
    public Shop toShop(ShopRequest shopRequest) {
        if ( shopRequest == null ) {
            return null;
        }

        Shop shop = new Shop();

        shop.setId( shopRequest.getId() );
        shop.setName( shopRequest.getName() );
        shop.setAddress( shopRequest.getAddress() );
        shop.setPhoneNumber( shopRequest.getPhoneNumber() );

        return shop;
    }

    @Override
    public ShopResponse toShopResponse(Shop shop) {
        if ( shop == null ) {
            return null;
        }

        ShopResponse shopResponse = new ShopResponse();

        shopResponse.setUrl( shopImagePath( shop ) );
        shopResponse.setId( shop.getId() );
        shopResponse.setName( shop.getName() );
        shopResponse.setAddress( shop.getAddress() );
        shopResponse.setPhoneNumber( shop.getPhoneNumber() );
        shopResponse.setClosed( shop.isClosed() );
        shopResponse.setMostPopular( shop.isMostPopular() );

        return shopResponse;
    }

    @Override
    public List<ShopResponse> toShopResponses(List<Shop> shopList) {
        if ( shopList == null ) {
            return null;
        }

        List<ShopResponse> list = new ArrayList<ShopResponse>( shopList.size() );
        for ( Shop shop : shopList ) {
            list.add( toShopResponse( shop ) );
        }

        return list;
    }

    @Override
    public ShopDataTable toShopDataTable(Shop shop) {
        if ( shop == null ) {
            return null;
        }

        ShopDataTable shopDataTable = new ShopDataTable();

        shopDataTable.setImage( shopImagePath( shop ) );
        shopDataTable.setResolved( shop.isMostPopular() );
        shopDataTable.setId( shop.getId() );
        shopDataTable.setName( shop.getName() );
        shopDataTable.setAddress( shop.getAddress() );
        shopDataTable.setPhoneNumber( shop.getPhoneNumber() );
        shopDataTable.setClosed( shop.isClosed() );

        return shopDataTable;
    }

    @Override
    public ShopForm toShopForm(Shop shop) {
        if ( shop == null ) {
            return null;
        }

        ShopForm shopForm = new ShopForm();

        shopForm.setUrl( shopImagePath( shop ) );
        shopForm.setId( shop.getId() );
        shopForm.setName( shop.getName() );
        shopForm.setAddress( shop.getAddress() );
        shopForm.setPhoneNumber( shop.getPhoneNumber() );

        return shopForm;
    }

    @Override
    public Shop toShop(ShopForm shopForm) {
        if ( shopForm == null ) {
            return null;
        }

        Shop shop = new Shop();

        shop.setName( shopForm.getName() );
        shop.setAddress( shopForm.getAddress() );
        shop.setPhoneNumber( shopForm.getPhoneNumber() );

        return shop;
    }

    @Override
    public Shop toShop(Shop shop, ShopForm shopForm) {
        if ( shop == null && shopForm == null ) {
            return null;
        }

        Shop shop1 = new Shop();

        if ( shop != null ) {
            shop1.setImage( shop.getImage() );
            shop1.setId( shop.getId() );
            shop1.setCreatedDate( shop.getCreatedDate() );
            shop1.setLastModifiedDate( shop.getLastModifiedDate() );
            shop1.setClosed( shop.isClosed() );
            shop1.setMostPopular( shop.isMostPopular() );
            List<ShopMenu> list = shop.getShopMenuList();
            if ( list != null ) {
                shop1.setShopMenuList( new ArrayList<ShopMenu>( list ) );
            }
        }
        if ( shopForm != null ) {
            shop1.setName( shopForm.getName() );
            shop1.setAddress( shopForm.getAddress() );
            shop1.setPhoneNumber( shopForm.getPhoneNumber() );
        }

        return shop1;
    }

    @Override
    public Shop toShop(Shop shop, PopularShopForm shopForm) {
        if ( shop == null && shopForm == null ) {
            return null;
        }

        Shop shop1 = new Shop();

        if ( shop != null ) {
            shop1.setId( shop.getId() );
            shop1.setCreatedDate( shop.getCreatedDate() );
            shop1.setLastModifiedDate( shop.getLastModifiedDate() );
            shop1.setClosed( shop.isClosed() );
            List<ShopMenu> list = shop.getShopMenuList();
            if ( list != null ) {
                shop1.setShopMenuList( new ArrayList<ShopMenu>( list ) );
            }
        }
        if ( shopForm != null ) {
            shop1.setAddress( shopForm.getAddress() );
            shop1.setPhoneNumber( shopForm.getPhoneNumber() );
            shop1.setName( shopForm.getName() );
            if ( shopForm.getIsMostPopular() != null ) {
                shop1.setMostPopular( shopForm.getIsMostPopular() );
            }
        }

        return shop1;
    }

    private String shopImagePath(Shop shop) {
        if ( shop == null ) {
            return null;
        }
        ShopImage image = shop.getImage();
        if ( image == null ) {
            return null;
        }
        String path = image.getPath();
        if ( path == null ) {
            return null;
        }
        return path;
    }
}
