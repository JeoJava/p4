package xm.project.p4.sp.service.impl;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import xm.project.p4.sp.dao.UserDao;
import xm.project.p4.sp.model.User;
import xm.project.p4.sp.service.UserService;
import xm.project.p4.sp.util.PagingUtil;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> users(int page, int size) {
        PagingUtil.checkPageAndSize(page, size);
        return userDao.findAll(PageRequest.of(page, size)).toList();
    }

    @Override
    public User register(User user) {
        if (user == null) {
            return null;
        }
        // 手动设置默认值
        setDefaultValue(user);
        return userDao.save(user);
    }

    // 为user设置默认值, 使用jpa时, 数据库中的默认不会生效, 因此在这里统一设置默认值
    private void setDefaultValue(User user) {
        if (user.getUsername() == null) {
            String phoneNo = user.getPhoneNo();
            user.setUsername("手机用户" + phoneNo.substring(0, 4) + "xxxxx" + phoneNo.substring(7, 11));
        }
        if (user.getSex() == null) {
            user.setSex("未知");
        }
        if (user.getType() == null) {
            user.setType(0);
        }
        if (user.getRegisterTime() == null) {
            user.setRegisterTime(LocalDate.now());
        }
    }

    @Override
    public User login(String phoneNo, String password) {
        return userDao.findByPhoneNoAndPassword(phoneNo, password);
    }

    @Override
    public User cancel(User user) {
        userDao.delete(user);
        return user;
    }

    @Override
    public User update(User user) {
        if (user.getId() == null) {
            return null;
        }
        User origin = userDao.findById(user.getId()).orElse(null);
        if (origin == null) {
            return null;
        }
        if (user.getUsername() != null) {
            origin.setUsername(user.getUsername());
        }
        if (user.getUserPassword() != null) {
            origin.setPassword(user.getUserPassword());
        }
        if (user.getSex() != null) {
            origin.setSex(user.getSex());
        }
        if (user.getType() != null) {
            origin.setType(user.getType());
        }
        if (user.getRegisterTime() != null) {
            origin.setRegisterTime(user.getRegisterTime());
        }
        return userDao.save(origin);
    }

    @Override
    public Boolean exists(String phoneNo) {
        return userDao.findByPhoneNo(phoneNo) != null;
    }
}
