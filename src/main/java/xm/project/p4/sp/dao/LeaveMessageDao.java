package xm.project.p4.sp.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import xm.project.p4.sp.model.LeaveMessage;

import java.util.List;

// 留言与数据库的接口
@Repository
public interface LeaveMessageDao extends PagingAndSortingRepository<LeaveMessage, Integer> {
    /**
     * 根据[uid], 查找指定用户的留言
     *
     * @param uid      用户编号
     * @param pageable 分页对象
     * @return 指定用户的留言
     */
    List<LeaveMessage> findByUid(Integer uid, Pageable pageable);

    /**
     * 根据[mid], 查找指定药品的留言
     *
     * @param mid      药品编号
     * @param pageable 分页对象
     * @return 指定用户的留言
     */
    List<LeaveMessage> findByMid(Integer mid, Pageable pageable);

    /**
     * 删除指定[uid]的所有留言
     *
     * @param uid 用户编号
     */
    void deleteByUid(Integer uid);

    /**
     * 删除指定[mid]的所有留言
     *
     * @param mid 药品编号
     */
    void deleteByMid(Integer mid);
}
