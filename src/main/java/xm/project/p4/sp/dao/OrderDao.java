package xm.project.p4.sp.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import xm.project.p4.sp.model.Order;

import java.util.List;

// 订单与数据库的接口
@Component
public interface OrderDao extends PagingAndSortingRepository<Order, Integer> {
    /**
     * 根据用户Id来查找订单
     *
     * @param uid      用户Id
     * @param pageable 分页属性
     * @return 用户订单
     */
    List<Order> findByUid(Integer uid, Pageable pageable);

    /**
     * 删除某一用户的所有订单
     *
     * @param uid 用户id
     */
    void deleteByUid(Integer uid);
}
