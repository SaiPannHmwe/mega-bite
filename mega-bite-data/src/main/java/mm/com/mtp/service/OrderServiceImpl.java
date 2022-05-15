package mm.com.mtp.service;

import lombok.extern.slf4j.Slf4j;
import mm.com.mtp.datatable.OrderDataTable;
import mm.com.mtp.exception.ResourceNotFoundException;
import mm.com.mtp.form.CartForm;
import mm.com.mtp.mapper.OrderMapper;
import mm.com.mtp.model.Cart;
import mm.com.mtp.model.Order;
import mm.com.mtp.model.ShopMenu;
import mm.com.mtp.repository.MenuRepository;
import mm.com.mtp.repository.OrderRepository;
import mm.com.mtp.repository.ShopMenuRepository;
import mm.com.mtp.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sai Pann Hmwe on 3/21/2022.
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ShopRepository shopRepository;

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    ShopMenuRepository shopMenuRepository;

    @Override
    public DataTablesOutput<OrderDataTable> orderDataTable(DataTablesInput dataTablesInput) {
        dataTablesInput.getOrder().add(new org.springframework.data.jpa.datatables.mapping.Order(0, "asc"));

        return orderRepository.findAll(dataTablesInput, filterByConditions(), null, orderMapper::toOrderDataTable);

    }

    @Override
    public OrderDataTable getOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "Id", id));

        return orderMapper.toOrderDataTable(order);
    }

    @Override
    public void saveOrder(OrderDataTable orderForm) {
        Order order = orderRepository.findById(orderForm.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Order", "Id", orderForm.getId()));

        order = orderMapper.toOrder(order, orderForm);
        orderRepository.save(order);
    }

    @Override
    public List<Long> getCollectCounts() {
        List<Long> collectCounts = new ArrayList<>();

        Long orderCount = orderRepository.count();
        Long shopCount = shopRepository.count();
        Long menuCount = menuRepository.count();

        collectCounts.add(shopCount);
        collectCounts.add(orderCount);
        collectCounts.add(menuCount);

        return collectCounts;
    }

    @Override
    public void creatOrder(CartForm cartForm, List<Cart> list) {
        int total = 0, count = 0;

        Order order = orderMapper.toOrder(cartForm, LocalDate.now());

        for (Cart cart : list) {
            total += cart.getPrice();
            count += cart.getQuantity();
            ShopMenu shopMenu = shopMenuRepository.findById(cart.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("ShopMenu", "Id", cart.getId()));
            order.addOrderDetail(order, shopMenu, cart);
        }

        order.setTotalAmount(total);
        order.setTotalCount(count);
        orderRepository.save(order);
    }

    private Specification<Order> filterByConditions() {
        return new Specification<Order>() {
            @Override
            public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                /*filterConditions.forEach((key, value) ->
                        predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get(key), value))));*/

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }

}
