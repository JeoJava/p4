package xm.project.p4.sp.service;

import xm.project.p4.sp.model.LeaveMessage;

import java.util.List;

// 留言服务层接口
public interface LeaveMessageService {
    /**
     * 分页查询留言
     *
     * @param page 页面下标 from 0
     * @param size 页面大小
     * @return 留言列表
     */
    List<LeaveMessage> all(int page, int size);

    /**
     * 总的分页数量
     *
     * @param pageSize 分页大小
     * @return 分页数量
     */
    int totalPageCount(int pageSize);

    /**
     * 根据[uid], 分页查询指定用户的留言
     *
     * @param uid  用户Id
     * @param page 页面下标
     * @param size 页面大小
     * @return 指定用户的留言
     */
    List<LeaveMessage> allByUid(Integer uid, int page, int size);

    /**
     * 根据[mid], 分页查询指定药品的留言
     *
     * @param mid  药品编号
     * @param page 页面下标
     * @param size 页面大小
     * @return 指定用户的留言
     */
    List<LeaveMessage> allByMid(Integer mid, int page, int size);

    /**
     * 新增留言
     *
     * @param message 要新增的留言
     * @return 新增的留言, 新增失败返回null
     */
    LeaveMessage add(LeaveMessage message);

    /**
     * 根据留言[id]删除指定留言
     *
     * @param id 留言Id
     * @return 删除成功返回true, 失败false
     */
    Boolean delete(Integer id);

    /**
     * 删除[ids]中指定的多个留言
     *
     * @param ids 要删除的留言编号列表
     * @return 删除成功返回true, 失败false
     */
    Boolean delete(List<Integer> ids);

    /**
     * 删除指定用户[uid]的所有留言
     *
     * @param uid 用户Id
     * @return 删除成功返回true, 失败false
     */
    Boolean deleteAllByUid(Integer uid);

    /**
     * 删除指定药品[mid]的所有留言
     *
     * @param mid 药品编号
     * @return 删除成功返回true, 失败false
     */
    Boolean deleteAllByMid(Integer mid);

    /**
     * 更新留言
     *
     * @param message 新的留言
     * @return 更新后的留言, 失败返回null
     */
    LeaveMessage update(LeaveMessage message);
}
