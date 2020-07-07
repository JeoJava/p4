package xm.project.p4.sp.service;

import xm.project.p4.sp.model.OrderRecord;

import java.util.List;

public interface OrderRecordService {
    /**
     * 添加新的订单记录
     *
     * @param records 记录
     */
    void add(List<OrderRecord> records);

    /**
     * 分页获取订购记录
     *
     * @param page 分页下标
     * @param size 分页大小
     */
    List<OrderRecord> all(int page, int size);

    /**
     * 获取指定订单的记录
     *
     * @param oid  订单编号
     * @param page 分页下标
     * @param size 分页大小
     */
    List<OrderRecord> allByOid(Integer oid, int page, int size);
}
