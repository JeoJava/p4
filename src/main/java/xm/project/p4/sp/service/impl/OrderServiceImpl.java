package xm.project.p4.sp.service.impl;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import xm.project.p4.sp.dao.OrderDao;
import xm.project.p4.sp.dao.OrderRecordDao;
import xm.project.p4.sp.model.Order;
import xm.project.p4.sp.model.OrderRecord;
import xm.project.p4.sp.service.OrderService;
import xm.project.p4.sp.util.PagingUtil;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRecordDao orderRecordDao;
    private final OrderDao orderDao;

    public OrderServiceImpl(OrderDao orderDao, OrderRecordDao orderRecordDao) {
        this.orderDao = orderDao;
        this.orderRecordDao = orderRecordDao;
    }

    @Override
    public int totalPageCount(int pageSize) {
        int count = (int) orderDao.count();
        return PagingUtil.totalPageCount(count, pageSize);
    }

    @Override
    public Order update(Order update) {
        Order order = orderDao.findById(update.getId()).orElse(null);
        if (order == null) {
            return null;
        }
        if (update.getId() != null) {
            order.setOid(update.getId());
        }
        if (update.getUid() != null) {
            order.setUid(update.getUid());
        }
        if (update.getState() != null) {
            order.setState(update.getState());
        }
        if (update.getOrderTime() != null) {
            order.setOrderTime(update.getOrderTime());
        }
        return orderDao.save(order);
    }

    @Override
    public List<Order> findAll(Integer uid, int page, int size) {
        PagingUtil.checkPageAndSize(page, size);
        if (uid == null) {
            return orderDao.findAll(PageRequest.of(page, size)).toList();
        } else {
            return orderDao.findByUid(uid, PageRequest.of(page, size));
        }
    }

    @Override
    public void delete(Integer id) {
        orderDao.deleteById(id);
    }

    @Transactional
    @Override
    public void delete(List<Integer> ids) {
        List<Order> orders = ids.stream().map(Order::new).collect(Collectors.toList());
        orderDao.deleteAll(orders);
    }

    @Transactional
    @Override
    public void deleteAllByUid(Integer uid) {
        orderDao.deleteByUid(uid);
    }

    @Transactional
    @Override
    public void add(Integer uid, List<OrderRecord> records) {
        Order order = new Order();
        order.setUid(uid);
        // 默认未付款
        order.setState(0);
        order.setOrderTime(LocalDate.now());

        Order savedOrder = orderDao.save(order);
        records.forEach(it -> it.setOid(savedOrder.getId()));
        orderRecordDao.saveAll(records);
    }

    @Override
    public void addOnlyOrder(Order order) {
        orderDao.save(order);
    }
}
