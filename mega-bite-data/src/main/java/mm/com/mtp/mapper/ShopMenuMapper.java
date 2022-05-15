package mm.com.mtp.mapper;

import mm.com.mtp.datatable.ShopMenuDataTable;
import mm.com.mtp.form.ShopMenuForm;
import mm.com.mtp.model.Menu;
import mm.com.mtp.model.Shop;
import mm.com.mtp.model.ShopMenu;
import org.mapstruct.*;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.List;

/**
 * Created by Sai Pann Hmwe on 4/6/2022.
 */
@Mapper(componentModel = "spring")
public abstract class ShopMenuMapper {

    @Mappings({
            @Mapping(target = "shopId", source = "shopMenu.shop.id"),
            @Mapping(target = "menuId", source = "shopMenu.menu.id"),
            @Mapping(target = "image", ignore = true),
            @Mapping(target = "url", source = "shopMenu.image.path")
    })
    public abstract ShopMenuForm toShopMenuForm(ShopMenu shopMenu);

    public abstract List<ShopMenuForm> toShopMenuForm(List<ShopMenu> shopMenu);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "lastModifiedDate", ignore = true),
            @Mapping(target = "image", ignore = true)
    })
    public abstract ShopMenu toShopMenu(Shop shop, Menu menu, ShopMenuForm shopMenuForm);

    @Mappings({
            @Mapping(target = "id", source = "shopMenu.id"),
            @Mapping(target = "shop", source = "shop"),
            @Mapping(target = "menu", source = "menu"),
            @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "lastModifiedDate", ignore = true),
            @Mapping(target = "price", source = "shopMenuForm.price"),
            @Mapping(target = "image", source = "shopMenu.image")
    })
    public abstract ShopMenu toShopMenu(ShopMenu shopMenu, Shop shop, Menu menu, ShopMenuForm shopMenuForm);

    @Mappings({
            @Mapping(target = "id", source = "shopMenu.id"),
            @Mapping(target = "price", source = "shopMenu.price"),
            @Mapping(target = "image", source = "shopMenu.image.path")
    })
    public abstract ShopMenuDataTable toShopMenuDataTable(ShopMenu shopMenu);

    public abstract List<ShopMenuDataTable> toShopMenuDataTable(List<ShopMenu> shopMenu);

    @AfterMapping
    protected void afterToShopMenuDataTable(ShopMenu shopMenu, @MappingTarget ShopMenuDataTable shopMenuDataTable) {
        if (LocaleContextHolder.getLocale().getLanguage().equals("en"))
            shopMenuDataTable.setName(shopMenu.getMenu().getName());
        else shopMenuDataTable.setName(shopMenu.getMenu().getNameChinese());
        ;
    }
}
