package xm.project.p4.sp.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import xm.project.p4.sp.model.OrderRecord;

import java.util.List;

@Repository
public interface OrderRecordDao extends PagingAndSortingRepository<OrderRecord, Integer> {
    List<OrderRecord> findByOid(Integer oid);
}
