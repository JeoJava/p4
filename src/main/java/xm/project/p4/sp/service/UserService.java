package xm.project.p4.sp.service;

import xm.project.p4.sp.model.User;

import java.util.List;

// 用户服务层接口
public interface UserService {
    /**
     * 判断具有某一电话号码的用户是否存在
     *
     * @param phoneNo 电话号码
     * @return boolean
     */
    Boolean exists(String phoneNo);

    /**
     * 用户注册
     *
     * @param user 接受一个注册的用户
     * @return 注册结果, 成功返回user, 失败返回null
     */
    User register(User user);

    /**
     * 用户登录
     *
     * @param phoneNo  电话号码
     * @param password 登录密码
     * @return 登录结果, 登录成功返回user, 失败返回null
     */
    User login(String phoneNo, String password);

    /**
     * 用户注销
     *
     * @param user 要注销的用户
     * @return 注销结果, 成功返回user, 失败返回null
     */
    User cancel(User user);

    /**
     * 分页得到用户
     *
     * @param page 页面下标 从0开始
     * @param size 页面大小
     * @return 用户列表
     */
    List<User> users(int page, int size);

    /**
     * 用户信息修改
     *
     * @param newUser 新的用户信息
     * @return 修改后的用户, 失败返回null
     */
    User update(User newUser);
}
