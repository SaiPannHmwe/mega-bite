package mm.com.mtp.service;

import mm.com.mtp.datatable.OrderDetailDataTable;
import mm.com.mtp.exception.ResourceNotFoundException;
import mm.com.mtp.mapper.OrderDetailMapper;
import mm.com.mtp.model.OrderDetail;
import mm.com.mtp.model.ShopMenu;
import mm.com.mtp.repository.OrderDetailRepository;
import mm.com.mtp.repository.OrderRepository;
import mm.com.mtp.repository.ShopMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.datatables.mapping.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Sai Pann Hmwe on 4/10/2022.
 */
@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    ShopMenuRepository shopMenuRepository;

    @Autowired
    OrderDetailMapper orderDetailMapper;

    @Override
    public DataTablesOutput<OrderDetailDataTable> orderDetailDataTable(DataTablesInput dataTablesInput, Map<String, Object> filterConditions) {
        dataTablesInput.getOrder().add(new Order(0, "asc"));
        return orderDetailRepository.findAll(dataTablesInput, filterByConditions(filterConditions), null, orderDetailMapper::toOrderDetailDataTable);

    }

    @Override
    public void createOrderDetail(Long orderId, Long menuId, int quantity) {
        mm.com.mtp.model.Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "Id", orderId));

        ShopMenu shopMenu = shopMenuRepository.findById(menuId)
                .orElseThrow(() -> new ResourceNotFoundException("ShopMenu", "Id", menuId));

        OrderDetail orderDetail = orderDetailMapper.toOrderDetail(order, shopMenu, quantity);
        orderDetail.setTotalAmount(shopMenu.getPrice() * quantity);
        orderDetailRepository.save(orderDetail);
    }

    @Override
    public void updateOrderDetail(Long orderId, Long menuId, Long orderDetailId, int quantity) {
        mm.com.mtp.model.Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "Id", orderId));

        ShopMenu shopMenu = shopMenuRepository.findById(menuId)
                .orElseThrow(() -> new ResourceNotFoundException("ShopMenu", "Id", menuId));

        OrderDetail orderDetail = orderDetailRepository.findById(orderDetailId)
                .orElseThrow(() -> new ResourceNotFoundException("OrderDetail", "Id", orderDetailId));

        orderDetail = orderDetailMapper.toOrderDetail(order, shopMenu, orderDetail, quantity);
        orderDetail.setTotalAmount(shopMenu.getPrice() * quantity);
        orderDetailRepository.save(orderDetail);

    }

    @Override
    public void deleteOrderDetail(Long id) {
        OrderDetail orderDetail = orderDetailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrderDetail", "Id", id));

        orderDetailRepository.delete(orderDetail);
    }

    private Specification<OrderDetail> filterByConditions(Map<String, Object> filterConditions) {
        return new Specification<OrderDetail>() {
            @Override
            public Predicate toPredicate(Root<OrderDetail> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                for (String key : filterConditions.keySet()) {

                    if (key.equals("orderId")) {
                        Join<OrderDetail, Order> orderJoin = root.join("order");
                        predicates.add(criteriaBuilder.and(criteriaBuilder.lower(orderJoin.get("id")).in(filterConditions.get(key))));
                    }
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
