package xm.project.p4.sp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

// 药品实体类
@NoArgsConstructor
@Entity
@Table(name = "medicine")
public class Medicine {
    public Medicine(Integer id) {
        this.id = id;
    }

    // 药品编号
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Getter
    private Integer id;

    // 为了适应前端框架
    public void setMid(Integer id) {
        this.id = id;
    }

    // 药品名称
    @Getter
    @Setter
    private String name;

    // 药品类别
    @Getter
    @Setter
    private String type;

    // 药品规格
    @Getter
    @Setter
    private String specifications;

    @Getter
    @Setter
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate productionTime;

    // 保质期
    @Getter
    @Setter
    private Double shelfLife;

    // 剩余库存
    @Getter
    @Setter
    private Double remainAmount;

    // 药品进价
    @Getter
    @Setter
    private Double inputPrice;

    // 药品售价
    @Getter
    @Setter
    private Double outputPrice;

    // 药品图片链接
    @Getter
    @Setter
    private String src;
}
