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
public class Menu extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String nameChinese;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShopMenu> shopMenuList = new ArrayList<>();

}
