package xm.project.p4.sp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xm.project.p4.sp.JqGridModel;
import xm.project.p4.sp.model.Order;
import xm.project.p4.sp.model.OrderRecord;
import xm.project.p4.sp.model.User;
import xm.project.p4.sp.service.OrderService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

// 订单控制类
@RequestMapping("/order")
@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping("/all")
    public JqGridModel<Order> orders(Integer uid, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "20") int rows) {
        JqGridModel<Order> orderJqGridModel = new JqGridModel<>();
        orderJqGridModel.setPage(page);
        try {
            orderJqGridModel.setRows(orderService.findAll(uid, page - 1, rows));
            orderJqGridModel.setTotal(orderService.totalPageCount(rows));
        } catch (Exception e) {
            orderJqGridModel.setRows(Collections.emptyList());
        }
        return orderJqGridModel;
    }

    // 处理增删改操作
    @RequestMapping("/handle")
    public Boolean handle(String oper, @RequestParam(name = "id") List<String> ids, Order order) {
        try {
            if ("edit".equals(oper)) {
                if (order.getId() == null) {
                    order.setOid(Integer.parseInt(ids.get(0)));
                }
                return update(order) != null;
            } else if ("del".equals(oper)) {
                List<Integer> integers = ids.stream().map((Integer::parseInt)).collect(Collectors.toList());
                return delete(integers);
            } else if ("add".equals(oper)) {
                try {
                    orderService.addOnlyOrder(order);
                    return true;
                } catch (Exception e) {
                    return false;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @RequestMapping("/update")
    public Order update(Order order) {
        try {
            return orderService.update(order);
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping("/delete")
    public Boolean delete(@RequestParam(name = "id") List<Integer> ids) {
        if (ids == null || ids.isEmpty()) {
            return false;
        }
        try {
            orderService.delete(ids);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @RequestMapping("/delete_all_by_uid")
    public Boolean deleteAllByUid(Integer uid) {
        if (uid == null) {
            return false;
        }
        try {
            orderService.deleteAllByUid(uid);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @RequestMapping("/add")
    public Boolean add(@RequestParam(name = "mid") List<Integer> mids, @RequestParam(name = "boughtAmount") List<Integer> boughtAmounts, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Integer id = user.getId();
        List<OrderRecord> records = new ArrayList<>(mids.size());
        for (int i = 0; i < mids.size(); i++) {
            records.add(new OrderRecord(mids.get(i), boughtAmounts.get(i)));
        }
        try {
            orderService.add(id, records);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
