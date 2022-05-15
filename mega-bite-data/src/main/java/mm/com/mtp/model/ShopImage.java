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
public class ShopImage extends FileProperty {

    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Shop shop;

    public ShopImage(Shop shop, String name, String type, Long size, String path) {
        this.shop = shop;
        this.setName(name);
        this.setType(type);
        this.setSize(size);
        this.setPath(path);
    }

}
