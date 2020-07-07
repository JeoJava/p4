package xm.project.p4.sp.service;

import xm.project.p4.sp.model.Medicine;

import java.util.List;

// 药品服务层接口
public interface MedicineService {

    /**
     * 根据pageSize计算分页数量
     *
     * @param pageSize 分页大小
     * @return 分页数量
     */
    int totalPageCount(int pageSize);

    /**
     * 分页查找药品
     *
     * @param name 药品名 用于模糊查询, 若为空, 则为一般查询
     * @param page 页面下标
     * @param size 页面大小
     * @return 药品
     */
    List<Medicine> findAll(String name, int page, int size);

    /**
     * 修改药品信息
     *
     * @param medicine 新的药品信息
     * @return 返回修改后的药品, 失败返回null
     */
    Medicine update(Medicine medicine);

    /**
     * 通过id从数据库中删除药品信息
     *
     * @param ids 要删除的药品id列表
     * @return 删除结果
     */
    Boolean delete(List<Integer> ids);

    /**
     * 新增药品
     *
     * @param medicine 药品
     * @return 药品
     */
    Medicine add(Medicine medicine);
}
