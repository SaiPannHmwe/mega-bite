package mm.com.mtp.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Sai Pann Hmwe on 4/5/2022.
 */
@Data
@Entity
public class ShopMenu extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Menu menu;

    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    private Integer price = 0;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "shopMenu")
    private ShopMenuImage image;

}
