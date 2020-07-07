package xm.project.p4.sp.service.impl;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import xm.project.p4.sp.dao.MedicineDao;
import xm.project.p4.sp.model.Medicine;
import xm.project.p4.sp.service.MedicineService;
import xm.project.p4.sp.util.PagingUtil;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicineServiceImpl implements MedicineService {

    private final MedicineDao medicineDao;

    public MedicineServiceImpl(MedicineDao medicineDao) {
        this.medicineDao = medicineDao;
    }

    @Override
    public int totalPageCount(int pageSize) {
        int count = (int) medicineDao.count();
        return PagingUtil.totalPageCount(count, pageSize);
    }

    @Override
    public List<Medicine> findAll(String name, int page, int size) {
        PagingUtil.checkPageAndSize(page, size);
        PageRequest pageRequest = PageRequest.of(page, size);
        if (name == null) {
            return medicineDao.findAll(pageRequest).toList();
        } else {
            return medicineDao.findByNameLike("%" + name + "%", pageRequest);
        }
    }

    @Override
    public Medicine update(Medicine medicine) {
        Medicine origin = medicineDao.findById(medicine.getId()).orElse(null);
        if (origin == null) {
            return null;
        }
        if (medicine.getId() != null) {
            origin.setMid(medicine.getId());
        }
        if (medicine.getInputPrice() != null) {
            origin.setInputPrice(medicine.getInputPrice());
        }
        if (medicine.getOutputPrice() != null) {
            origin.setOutputPrice(medicine.getOutputPrice());
        }
        if (medicine.getName() != null) {
            origin.setName(medicine.getName());
        }
        if (medicine.getProductionTime() != null) {
            origin.setProductionTime(medicine.getProductionTime());
        }
        if (medicine.getRemainAmount() != null) {
            origin.setRemainAmount(medicine.getRemainAmount());
        }
        if (medicine.getShelfLife() != null) {
            origin.setShelfLife(medicine.getShelfLife());
        }
        if (medicine.getSpecifications() != null) {
            origin.setSpecifications(medicine.getSpecifications());
        }
        if (medicine.getType() != null) {
            origin.setType(medicine.getType());
        }
        if (medicine.getSrc() != null) {
            origin.setSrc(medicine.getSrc());
        }
        return medicineDao.save(origin);
    }

    @Override
    public Boolean delete(List<Integer> ids) {
        List<Medicine> medicines = ids.stream().map(Medicine::new).collect(Collectors.toList());
        try {
            medicineDao.deleteAll(medicines);
            return true;
        } catch (Exception e) {
            System.out.println("MedicineServiceImpl delete传来消息, 删除出现异常了...");
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Medicine add(Medicine medicine) {
        if (medicine == null) {
            return null;
        }
        return medicineDao.save(medicine);
    }
}
