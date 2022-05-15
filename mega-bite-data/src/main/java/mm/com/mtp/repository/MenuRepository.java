package mm.com.mtp.repository;

import mm.com.mtp.model.Menu;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.DoubleStream;

/**
 * Created by Sai Pann Hmwe on 4/3/2022.
 */
@Repository
public interface MenuRepository extends JpaRepository<Menu, Long>, DataTablesRepository<Menu, Long> {

}
