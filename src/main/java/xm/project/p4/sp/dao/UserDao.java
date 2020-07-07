package xm.project.p4.sp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import xm.project.p4.sp.model.User;

// 用户与数据库的接口
@Component
public interface UserDao extends JpaRepository<User, Integer> {
    /**
     * 通过手机号来查找用户
     *
     * @param phoneNo 手机号
     * @return 某一手机号的用户, 不存在则返回null
     */
    User findByPhoneNo(String phoneNo);

    /**
     * 通过电话号码和用户密码来查找用户
     *
     * @param phoneNo  电话号码
     * @param password 用户密码
     * @return 查找的用户, 不存在, 则返回null
     */
    User findByPhoneNoAndPassword(String phoneNo, String password);
}
