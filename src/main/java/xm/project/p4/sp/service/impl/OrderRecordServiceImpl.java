package xm.project.p4.sp.service.impl;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import xm.project.p4.sp.dao.OrderRecordDao;
import xm.project.p4.sp.model.OrderRecord;
import xm.project.p4.sp.service.OrderRecordService;
import xm.project.p4.sp.util.PagingUtil;

import java.util.List;

@Service
public class OrderRecordServiceImpl implements OrderRecordService {
    private final OrderRecordDao orderRecordDao;

    public OrderRecordServiceImpl(OrderRecordDao orderRecordDao) {
        this.orderRecordDao = orderRecordDao;
    }

    @Override
    public void add(List<OrderRecord> records) {
        orderRecordDao.saveAll(records);
    }

    @Override
    public List<OrderRecord> all(int page, int size) {
        PagingUtil.checkPageAndSize(page, size);
        return orderRecordDao.findAll(PageRequest.of(page, size)).toList();
    }

    @Override
    public List<OrderRecord> allByOid(Integer oid, int page, int size) {
        PagingUtil.checkPageAndSize(page, size);
        return orderRecordDao.findByOid(oid);
    }
}
