package xm.project.p4.sp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xm.project.p4.sp.model.User;
import xm.project.p4.sp.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/users")
    public List<User> users(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        try {
            return userService.users(page, size);
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @RequestMapping("/update")
    public User update(User newUser, HttpSession session) {
        try {
            User user = userService.update(newUser);
            if (user != null) {
                session.setAttribute("user", user);
            }
            return user;
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping("/passwordChange")
    public User passwordChange(String oldPassword, String newPassword, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null || oldPassword == null || newPassword == null) {
            return null;
        }
        if (!user.getUserPassword().equals(oldPassword)) {
            return null;
        }
        user.setPassword(newPassword);
        try {
            return userService.update(user);
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping("/exists")
    public Boolean exists(String phoneNo) {
        try {
            return userService.exists(phoneNo);
        } catch (Exception e) {
            return false;
        }
    }

    @RequestMapping("/register")
    public User register(User user) {
        try {
            return userService.register(user);
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping("/login")
    public User login(String phoneNo, String password, HttpSession session) {
        try {
            User user = userService.login(phoneNo, password);
            if (user != null) {
                session.setAttribute("user", user);
            }
            return user;
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping("/logout")
    public User logout(HttpSession session) {
        try {
            User user = (User) session.getAttribute("user");
            if (user != null) {
                session.removeAttribute("user");
            }
            return user;
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping("/cancel")
    public User cancel(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return null;
        }
        try {
            return userService.cancel(user);
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping("/loginUser")
    public User loginUser(HttpSession session) {
        try {
            return (User) session.getAttribute("user");
        } catch (Exception e) {
            return null;
        }
    }
}

