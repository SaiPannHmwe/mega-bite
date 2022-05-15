package mm.com.mtp.mapper;

import mm.com.mtp.datatable.ShopDataTable;
import mm.com.mtp.form.PopularShopForm;
import mm.com.mtp.form.ShopForm;
import mm.com.mtp.model.Shop;
import mm.com.mtp.payload.ShopRequest;
import mm.com.mtp.payload.ShopResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * Created by Sai Pann Hmwe on 4/2/2022.
 */
@Mapper(componentModel = "spring")
public interface ShopMapper {

    Shop toShop(ShopRequest shopRequest);

    @Mapping(target = "url", source = "shop.image.path")
    ShopResponse toShopResponse(Shop shop);

    List<ShopResponse> toShopResponses(List<Shop> shopList);

    @Mappings({
            @Mapping(target = "resolved", source = "mostPopular"),
            @Mapping(target = "image", source = "shop.image.path")
    })
    ShopDataTable toShopDataTable(Shop shop);

    @Mappings({
            @Mapping(target = "image", ignore = true),
            @Mapping(target = "url", source = "shop.image.path")
    })
    ShopForm toShopForm(Shop shop);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "lastModifiedDate", ignore = true),
            @Mapping(target = "image", ignore = true)
    })
    Shop toShop(ShopForm shopForm);

    @Mappings({
            @Mapping(target = "id", source = "shop.id"),
            @Mapping(target = "name", source = "shopForm.name"),
            @Mapping(target = "address", source = "shopForm.address"),
            @Mapping(target = "phoneNumber", source = "shopForm.phoneNumber"),
            @Mapping(target = "image", source = "shop.image")
    })
    Shop toShop(Shop shop, ShopForm shopForm);

    @Mappings({
            @Mapping(target = "id", source = "shop.id"),
            @Mapping(target = "name", source = "shopForm.name"),
            @Mapping(target = "address", source = "shopForm.address"),
            @Mapping(target = "phoneNumber", source = "shopForm.phoneNumber"),
            @Mapping(target = "mostPopular", source = "shopForm.isMostPopular"),
            @Mapping(target = "image", ignore = true)
    })
    Shop toShop(Shop shop, PopularShopForm shopForm);

}
