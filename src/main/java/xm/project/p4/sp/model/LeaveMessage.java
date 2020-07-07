package xm.project.p4.sp.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

// 留言实体类
@Entity
@Table(name = "leave_msg")
public class LeaveMessage {
    // 留言编号
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 为了适应前端框架
    public void setLid(Integer id) {
        this.id = id;
    }

    // 用户编号
    @Getter
    @Setter
    private Integer uid;

    // 药品编号
    @Getter
    @Setter
    private Integer mid;

    // 留言内容
    @Getter
    @Setter
    private String msg;
}
