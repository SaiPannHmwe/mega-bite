package mm.com.mtp.repository;

import mm.com.mtp.model.Order;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Sai Pann Hmwe on 4/10/2022.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, DataTablesRepository<Order, Long> {

}
