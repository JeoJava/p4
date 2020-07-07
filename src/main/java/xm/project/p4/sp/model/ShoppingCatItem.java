package xm.project.p4.sp.model;

import lombok.Data;

// 购物
@Data
public class ShoppingCatItem {
    // 药品编号
    private Integer mid;
    // 药品名称
    private String name;
    // 购买数量
    private Integer boughtAmount;
}
