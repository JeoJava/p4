package xm.project.p4.sp.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import xm.project.p4.sp.model.Medicine;

import java.util.List;

// 药品与数据库的接口
@Component
public interface MedicineDao extends PagingAndSortingRepository<Medicine, Integer> {
    /**
     * 通过[name]模糊查询药品
     *
     * @param name     药品名称
     * @param pageable 分页对象
     * @return 药品列表
     */
    List<Medicine> findByNameLike(String name, Pageable pageable);
}
