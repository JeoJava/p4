package xm.project.p4.sp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

// 订单实体类
@NoArgsConstructor
@Entity
@Table(name = "user_order")
public class Order {
    public Order(Integer id) {
        this.id = id;
    }

    // 订单编号
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 为了适应前端框架
    public void setOid(Integer id) {
        this.id = id;
    }

    // 用户编号
    @Getter
    @Setter
    private Integer uid;

    // 订单状态 0未付款 1已付款 2已取消
    @Getter
    @Setter
    private Integer state;

    // 订购时间
    @Getter
    @Setter
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate orderTime;
}
