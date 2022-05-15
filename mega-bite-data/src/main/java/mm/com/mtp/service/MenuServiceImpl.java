package mm.com.mtp.service;

import mm.com.mtp.datatable.MenuDataTable;
import mm.com.mtp.exception.ResourceNotFoundException;
import mm.com.mtp.form.MenuForm;
import mm.com.mtp.mapper.MenuMapper;
import mm.com.mtp.model.Menu;
import mm.com.mtp.payload.MenuResponse;
import mm.com.mtp.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Sai Pann Hmwe on 4/3/2022.
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    MenuMapper menuMapper;


    @Override
    public DataTablesOutput<MenuDataTable> menuDataTable(DataTablesInput dataTablesInput) {
        dataTablesInput.getOrder().add(new org.springframework.data.jpa.datatables.mapping.Order(0, "asc"));

        return menuRepository.findAll(dataTablesInput, filterByConditions(), null, menuMapper::toMenuDataTable);

    }

    @Override
    public MenuForm getMenu(Long id) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menu", "Id", id));
        return menuMapper.toMenuForm(menu);
    }

    @Override
    public List<MenuResponse> getMenu(int start, int size) {
        Pageable pageable = PageRequest.of(start, size);
        Page<MenuResponse> menuRepositoryPage = menuRepository.findAll(pageable)
                .map(menuMapper::toMenuResponse);

        return menuRepositoryPage.getContent();
    }

    @Override
    public MenuResponse getMenuDetail(Long id) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menu", "Id", id));
        return menuMapper.toMenuResponse(menu);
    }

    @Override
    public void save(MenuForm menuForm) {
        Menu menu;
        if (menuForm.getId() == null) {
            menu = menuMapper.toMenu(menuForm);
        } else {
            menu = menuRepository.findById(menuForm.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Menu", "Id", menuForm.getId()));
            menu = menuMapper.toMenu(menu, menuForm);
        }
        menuRepository.save(menu);
    }

    @Override
    public Map<Long, String> getMenuMap() {
        return menuRepository.findAll().stream().collect(Collectors.toMap(Menu::getId, Menu::getName));
    }

    private Specification<Menu> filterByConditions() {
        return new Specification<Menu>() {
            @Override
            public Predicate toPredicate(Root<Menu> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                /*filterConditions.forEach((key, value) ->
                        predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get(key), value))));*/

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }

}
