package xm.project.p4.sp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xm.project.p4.sp.JqGridModel;
import xm.project.p4.sp.model.Medicine;
import xm.project.p4.sp.service.MedicineService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

// 药品控制类
@RequestMapping("/medicine")
@RestController
public class MedicineController {

    private final MedicineService medicineService;

    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @RequestMapping("/all")
    public JqGridModel<Medicine> findAll(String name, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "20") int rows) {
        JqGridModel<Medicine> medicineJqGridModel = new JqGridModel<>();
        medicineJqGridModel.setPage(page);
        medicineJqGridModel.setTotal(medicineService.totalPageCount(rows));
        try {
            List<Medicine> medicines = medicineService.findAll(name, page - 1, rows);
            medicineJqGridModel.setRows(medicines);
        } catch (Exception e) {
            medicineJqGridModel.setRows(Collections.emptyList());
        }
        return medicineJqGridModel;
    }

    // 处理增删改操作
    @RequestMapping("/handle")
    public Boolean handle(String oper, @RequestParam(name = "id") List<String> ids, Medicine medicine) {
        try {
            if ("edit".equals(oper)) {
                if (medicine.getId() == null) {
                    medicine.setMid(Integer.parseInt(ids.get(0)));
                }
                return update(medicine) != null;
            } else if ("del".equals(oper)) {
                List<Integer> integers = ids.stream().map((Integer::parseInt)).collect(Collectors.toList());
                return delete(integers);
            } else if ("add".equals(oper)) {
                return add(medicine) != null;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @RequestMapping("/update")
    public Medicine update(Medicine medicine) {
        try {
            return medicineService.update(medicine);
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping("/delete")
    public Boolean delete(@RequestParam(name = "id") List<Integer> ids) {
        try {
            return medicineService.delete(ids);
        } catch (Exception e) {
            return false;
        }
    }

    @RequestMapping("/add")
    public Medicine add(Medicine medicine) {
//        try {
//            return medicineService.add(medicine);
//        } catch (Exception e) {
//            return null;
//        }
        return medicineService.add(medicine);
    }
}
