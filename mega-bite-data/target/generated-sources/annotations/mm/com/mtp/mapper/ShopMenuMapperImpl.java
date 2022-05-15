package mm.com.mtp.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import mm.com.mtp.datatable.ShopMenuDataTable;
import mm.com.mtp.form.ShopMenuForm;
import mm.com.mtp.model.Menu;
import mm.com.mtp.model.Shop;
import mm.com.mtp.model.ShopMenu;
import mm.com.mtp.model.ShopMenuImage;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-11T20:44:15+0630",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_261 (Oracle Corporation)"
)
@Component
public class ShopMenuMapperImpl extends ShopMenuMapper {

    @Override
    public ShopMenuForm toShopMenuForm(ShopMenu shopMenu) {
        if ( shopMenu == null ) {
            return null;
        }

        ShopMenuForm shopMenuForm = new ShopMenuForm();

        shopMenuForm.setMenuId( shopMenuMenuId( shopMenu ) );
        shopMenuForm.setShopId( shopMenuShopId( shopMenu ) );
        shopMenuForm.setUrl( shopMenuImagePath( shopMenu ) );
        shopMenuForm.setId( shopMenu.getId() );
        shopMenuForm.setPrice( shopMenu.getPrice() );

        return shopMenuForm;
    }

    @Override
    public List<ShopMenuForm> toShopMenuForm(List<ShopMenu> shopMenu) {
        if ( shopMenu == null ) {
            return null;
        }

        List<ShopMenuForm> list = new ArrayList<ShopMenuForm>( shopMenu.size() );
        for ( ShopMenu shopMenu1 : shopMenu ) {
            list.add( toShopMenuForm( shopMenu1 ) );
        }

        return list;
    }

    @Override
    public ShopMenu toShopMenu(Shop shop, Menu menu, ShopMenuForm shopMenuForm) {
        if ( shop == null && menu == null && shopMenuForm == null ) {
            return null;
        }

        ShopMenu shopMenu = new ShopMenu();

        if ( shop != null ) {
            shopMenu.setShop( shop );
        }
        if ( menu != null ) {
            shopMenu.setMenu( menu );
        }
        if ( shopMenuForm != null ) {
            shopMenu.setPrice( shopMenuForm.getPrice() );
        }

        return shopMenu;
    }

    @Override
    public ShopMenu toShopMenu(ShopMenu shopMenu, Shop shop, Menu menu, ShopMenuForm shopMenuForm) {
        if ( shopMenu == null && shop == null && menu == null && shopMenuForm == null ) {
            return null;
        }

        ShopMenu shopMenu1 = new ShopMenu();

        if ( shopMenu != null ) {
            shopMenu1.setImage( shopMenu.getImage() );
            shopMenu1.setId( shopMenu.getId() );
        }
        if ( shop != null ) {
            shopMenu1.setShop( shop );
        }
        if ( menu != null ) {
            shopMenu1.setMenu( menu );
        }
        if ( shopMenuForm != null ) {
            shopMenu1.setPrice( shopMenuForm.getPrice() );
        }

        return shopMenu1;
    }

    @Override
    public ShopMenuDataTable toShopMenuDataTable(ShopMenu shopMenu) {
        if ( shopMenu == null ) {
            return null;
        }

        ShopMenuDataTable shopMenuDataTable = new ShopMenuDataTable();

        shopMenuDataTable.setImage( shopMenuImagePath( shopMenu ) );
        shopMenuDataTable.setId( shopMenu.getId() );
        shopMenuDataTable.setPrice( shopMenu.getPrice() );

        afterToShopMenuDataTable( shopMenu, shopMenuDataTable );

        return shopMenuDataTable;
    }

    @Override
    public List<ShopMenuDataTable> toShopMenuDataTable(List<ShopMenu> shopMenu) {
        if ( shopMenu == null ) {
            return null;
        }

        List<ShopMenuDataTable> list = new ArrayList<ShopMenuDataTable>( shopMenu.size() );
        for ( ShopMenu shopMenu1 : shopMenu ) {
            list.add( toShopMenuDataTable( shopMenu1 ) );
        }

        return list;
    }

    private Long shopMenuMenuId(ShopMenu shopMenu) {
        if ( shopMenu == null ) {
            return null;
        }
        Menu menu = shopMenu.getMenu();
        if ( menu == null ) {
            return null;
        }
        Long id = menu.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long shopMenuShopId(ShopMenu shopMenu) {
        if ( shopMenu == null ) {
            return null;
        }
        Shop shop = shopMenu.getShop();
        if ( shop == null ) {
            return null;
        }
        Long id = shop.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String shopMenuImagePath(ShopMenu shopMenu) {
        if ( shopMenu == null ) {
            return null;
        }
        ShopMenuImage image = shopMenu.getImage();
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
