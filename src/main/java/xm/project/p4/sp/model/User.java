package xm.project.p4.sp.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

// 用户实体, 包括管理员
@Entity
@Table(name = "user")
@Setter
public class User {
    // 用户编号
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Getter
    private Integer id;

    // 电话号码 登录账号
    @Getter
    private String phoneNo;

    // 用户昵称
    @Getter
    private String username;

    // 用户密码
    private String password;

    public String getUserPassword() {
        return password;
    }

    // 用户类型 0普通用户 1管理员
    @Getter
    private Integer type;

    // 用户性别
    @Getter
    private String sex;

    // 注册时间
    @Getter
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate registerTime;
}
