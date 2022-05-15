package mm.com.mtp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by Sai Pann Hmwe on 3/21/2022.
 */
@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class OrderDetail extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    private ShopMenu shopMenu;

    private Integer quantity = 0;

    private Integer totalAmount = 0;

    public OrderDetail(Order order, ShopMenu shopMenu, Cart cart) {
        this.order = order;
        this.shopMenu = shopMenu;
        this.quantity = cart.getQuantity();
        this.totalAmount = cart.getPrice();
    }

}
