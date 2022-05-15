package mm.com.mtp.repository;

import mm.com.mtp.model.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.DoubleStream;

/**
 * Created by Sai Pann Hmwe on 4/2/2022.
 */
@Repository
public interface ShopRepository extends JpaRepository<Shop, Long>, DataTablesRepository<Shop, Long> {

    Optional<Shop> findByIsMostPopular(boolean b);

    Page<Shop> findAllByNameContaining(String query, Pageable pageable);

}
