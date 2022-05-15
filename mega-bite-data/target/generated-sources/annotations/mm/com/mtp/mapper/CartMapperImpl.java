package mm.com.mtp.mapper;

import javax.annotation.Generated;
import mm.com.mtp.datatable.ShopMenuDataTable;
import mm.com.mtp.model.Cart;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-11T20:44:15+0630",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_261 (Oracle Corporation)"
)
@Component
public class CartMapperImpl implements CartMapper {

    @Override
    public Cart toCart(ShopMenuDataTable menuForm) {
        if ( menuForm == null ) {
            return null;
        }

        Cart cart = new Cart();

        cart.setName( menuForm.getName() );
        cart.setPrice( menuForm.getPrice() );

        return cart;
    }
}
