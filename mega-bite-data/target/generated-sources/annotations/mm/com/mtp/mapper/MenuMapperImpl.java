package mm.com.mtp.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import mm.com.mtp.datatable.MenuDataTable;
import mm.com.mtp.form.MenuForm;
import mm.com.mtp.model.Menu;
import mm.com.mtp.model.ShopMenu;
import mm.com.mtp.payload.MenuResponse;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-11T20:44:15+0630",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_261 (Oracle Corporation)"
)
@Component
public class MenuMapperImpl implements MenuMapper {

    @Override
    public MenuResponse toMenuResponse(Menu menu) {
        if ( menu == null ) {
            return null;
        }

        MenuResponse menuResponse = new MenuResponse();

        menuResponse.setId( menu.getId() );
        menuResponse.setName( menu.getName() );
        menuResponse.setNameChinese( menu.getNameChinese() );

        return menuResponse;
    }

    @Override
    public MenuDataTable toMenuDataTable(Menu menu) {
        if ( menu == null ) {
            return null;
        }

        MenuDataTable menuDataTable = new MenuDataTable();

        menuDataTable.setId( menu.getId() );
        menuDataTable.setName( menu.getName() );
        menuDataTable.setNameChinese( menu.getNameChinese() );

        return menuDataTable;
    }

    @Override
    public Menu toMenu(MenuForm menuForm) {
        if ( menuForm == null ) {
            return null;
        }

        Menu menu = new Menu();

        menu.setName( menuForm.getName() );
        menu.setNameChinese( menuForm.getNameChinese() );

        return menu;
    }

    @Override
    public Menu toMenu(Menu menu, MenuForm menuForm) {
        if ( menu == null && menuForm == null ) {
            return null;
        }

        Menu menu1 = new Menu();

        if ( menu != null ) {
            menu1.setId( menu.getId() );
            menu1.setCreatedDate( menu.getCreatedDate() );
            menu1.setLastModifiedDate( menu.getLastModifiedDate() );
            List<ShopMenu> list = menu.getShopMenuList();
            if ( list != null ) {
                menu1.setShopMenuList( new ArrayList<ShopMenu>( list ) );
            }
        }
        if ( menuForm != null ) {
            menu1.setName( menuForm.getName() );
            menu1.setNameChinese( menuForm.getNameChinese() );
        }

        return menu1;
    }

    @Override
    public MenuForm toMenuForm(Menu menu) {
        if ( menu == null ) {
            return null;
        }

        MenuForm menuForm = new MenuForm();

        menuForm.setId( menu.getId() );
        menuForm.setName( menu.getName() );
        menuForm.setNameChinese( menu.getNameChinese() );

        return menuForm;
    }
}
