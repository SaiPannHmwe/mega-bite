package mm.com.mtp.controller;

import lombok.extern.slf4j.Slf4j;
import mm.com.mtp.datatable.OrderDetailDataTable;
import mm.com.mtp.datatable.ShopMenuDataTable;
import mm.com.mtp.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sai Pann Hmwe on 4/10/2022.
 */
@Slf4j
@Controller
public class OrderDetailController {

    @Autowired
    OrderDetailService orderDetailService;

    @ResponseBody
    @PostMapping("/orderDetail/dataTable")
    public DataTablesOutput<OrderDetailDataTable> orderDetailDataTable(@RequestBody DataTablesInput dataTablesInput,
                                                                    @RequestParam Long orderId) {
        Map<String, Object> filterConditions = new HashMap<>();
        if (orderId > 0) filterConditions.put("orderId", orderId);
        DataTablesOutput<OrderDetailDataTable> db = orderDetailService.orderDetailDataTable(dataTablesInput, filterConditions);
        return db;
    }

}
