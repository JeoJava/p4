package xm.project.p4.sp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 订单记录实体, 记录的是订购的药品信息
 */
@Entity
@Table(name = "order_record")
@Data
@NoArgsConstructor
public class OrderRecord {
    public OrderRecord(Integer mid, Integer boughtAmount) {
        this.mid = mid;
        this.boughtAmount = boughtAmount;
    }

    // 订单编号
    private Integer oid;

    // 药品编号
    @Id
    private Integer mid;

    // 购买数量
    private Integer boughtAmount;
}
