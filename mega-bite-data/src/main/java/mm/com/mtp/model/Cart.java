package mm.com.mtp.model;

import lombok.Data;
import mm.com.mtp.datatable.ShopMenuDataTable;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Sai Pann Hmwe on 5/8/2022.
 */
@Data
@Entity
public class Cart {

    @Id
    private Long id;

    private String name;

    private Integer price = 0;

    private Integer quantity = 0;

    private String url;

    public void toCart(ShopMenuDataTable menu) {
        this.id = menu.getId();
        this.name = menu.getName();
        this.price = menu.getPrice();
        this.url = menu.getImage();
        this.quantity = 1;
    }

}
