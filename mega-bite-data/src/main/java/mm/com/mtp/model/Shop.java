package mm.com.mtp.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sai Pann Hmwe on 3/21/2022.
 */
@Data
@Entity
public class Shop extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String phoneNumber;

    private boolean isClosed;

    private boolean isMostPopular;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "shop")
    private ShopImage image;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "shop")
    private List<ShopMenu> shopMenuList = new ArrayList<>();

}
