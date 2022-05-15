package mm.com.mtp.mapper;

import mm.com.mtp.datatable.ShopMenuDataTable;
import mm.com.mtp.model.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * Created by Sai Pann Hmwe on 5/8/2022.
 */
@Mapper(componentModel = "spring")
public interface CartMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
    })
    Cart toCart(ShopMenuDataTable menuForm);
}
