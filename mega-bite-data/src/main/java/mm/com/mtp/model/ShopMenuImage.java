package mm.com.mtp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by Sai Pann Hmwe on 3/21/2022.
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class ShopMenuImage extends FileProperty {

    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private ShopMenu shopMenu;

    public ShopMenuImage(ShopMenu shopMenu, String name, String type, Long size, String path) {
        this.shopMenu = shopMenu;
        this.setName(name);
        this.setType(type);
        this.setSize(size);
        this.setPath(path);
    }

}
