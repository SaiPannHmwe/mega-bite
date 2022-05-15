package mm.com.mtp.repository;

import mm.com.mtp.model.ShopMenu;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Sai Pann Hmwe on 4/6/2022.
 */
@Repository
public interface ShopMenuRepository extends JpaRepository<ShopMenu, Long>, DataTablesRepository<ShopMenu, Long> {

    List<ShopMenu> findByShopId(Long shopId);

}
