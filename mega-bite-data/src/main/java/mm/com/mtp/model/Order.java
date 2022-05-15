package mm.com.mtp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sai Pann Hmwe on 3/21/2022.
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = "orders")
public class Order extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private String customerName;

    private String customerAddress;

    private String customerPhone;

    private Integer totalCount = 0;

    private Integer totalAmount = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderDetail> orderDetails = new ArrayList<>();

    public void addOrderDetail(Order order, ShopMenu shopMenu, Cart cart) {
        OrderDetail orderDetail = new OrderDetail(order, shopMenu, cart);
        orderDetails.add(orderDetail);
        orderDetail.setOrder(this);
    }

}
