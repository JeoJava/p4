package xm.project.p4.sp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xm.project.p4.sp.model.OrderRecord;
import xm.project.p4.sp.service.OrderRecordService;

import java.util.List;

@RequestMapping("/order_record")
@RestController
public class OrderRecordController {
    private final OrderRecordService orderRecordService;

    public OrderRecordController(OrderRecordService orderRecordService) {
        this.orderRecordService = orderRecordService;
    }

    @RequestMapping("/all")
    public List<OrderRecord> records(Integer oid, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "20") Integer rows) {
        try {
            if (oid != null) {
                return orderRecordService.allByOid(oid, page - 1, rows);
            } else {
                return orderRecordService.all(page - 1, rows);
            }
        } catch (Exception e) {
            return null;
        }
    }
}
