package xm.project.p4.sp.service;

import xm.project.p4.sp.model.Order;
import xm.project.p4.sp.model.OrderRecord;

import java.util.List;

// 订单服务接口
public interface OrderService {
    /**
     * 根据pageSize计算分页数量
     *
     * @param pageSize 分页大小
     * @return 分页数量
     */
    int totalPageCount(int pageSize);

    /**
     * 修改订单
     *
     * @param update 新的订单
     * @return 修改后的订单
     */
    Order update(Order update);

    /**
     * 分页查找订单
     *
     * @param uid  用户编号, 不为空则查询指定用户的订单, 为空则查询所有的订单
     * @param page 页面下标
     * @param size 页面大小
     * @return 订单
     */
    List<Order> findAll(Integer uid, int page, int size);

    /**
     * 删除指定id的订单
     *
     * @param id 要删除的订单id
     */
    void delete(Integer id);

    /**
     * 删除ids中的订单
     *
     * @param ids 要删除的订单编号列表
     */
    void delete(List<Integer> ids);

    /**
     * 删除某一指定用户的所有订单
     *
     * @param uid 用户id
     */
    void deleteAllByUid(Integer uid);

    /**
     * 添加订单
     *
     * @param uid     用户id
     * @param records 订单内容
     */
    void add(Integer uid, List<OrderRecord> records);

    /**
     * 单纯的只添加订单本身
     *
     * @param order 订单
     */
    void addOnlyOrder(Order order);
}
