package xm.project.p4.sp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xm.project.p4.sp.JqGridModel;
import xm.project.p4.sp.model.LeaveMessage;
import xm.project.p4.sp.model.User;
import xm.project.p4.sp.service.LeaveMessageService;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/leave_msg")
@RestController
public class LeaveMessageController {

    private final LeaveMessageService leaveMessageService;

    public LeaveMessageController(LeaveMessageService leaveMessageService) {
        this.leaveMessageService = leaveMessageService;
    }

    @RequestMapping("/all")
    public JqGridModel<LeaveMessage> all(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "20") int rows) {
        JqGridModel<LeaveMessage> messageJqGridModel = new JqGridModel<>();
        messageJqGridModel.setPage(page);
        messageJqGridModel.setTotal(leaveMessageService.totalPageCount(rows));
        try {
            List<LeaveMessage> messages = leaveMessageService.all(page - 1, rows);
            messageJqGridModel.setRows(messages);
        } catch (Exception e) {
            messageJqGridModel.setRows(Collections.emptyList());
        }
        return messageJqGridModel;
    }

    @RequestMapping("/all_by_uid")
    public JqGridModel<LeaveMessage> allByUid(Integer uid, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "20") int rows) {
        JqGridModel<LeaveMessage> messageJqGridModel = new JqGridModel<>();
        messageJqGridModel.setPage(page);
        messageJqGridModel.setTotal(leaveMessageService.totalPageCount(rows));
        try {
            List<LeaveMessage> messages = leaveMessageService.allByUid(uid, page - 1, rows);
            messageJqGridModel.setRows(messages);
        } catch (Exception e) {
            messageJqGridModel.setRows(Collections.emptyList());
        }
        return messageJqGridModel;
    }

    @RequestMapping("/all_by_mid")
    public JqGridModel<LeaveMessage> allByMid(Integer mid, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "20") int rows) {
        JqGridModel<LeaveMessage> messageJqGridModel = new JqGridModel<>();
        messageJqGridModel.setPage(page);
        messageJqGridModel.setTotal(leaveMessageService.totalPageCount(rows));
        try {
            List<LeaveMessage> messages = leaveMessageService.allByMid(mid, page - 1, rows);
            messageJqGridModel.setRows(messages);
        } catch (Exception e) {
            messageJqGridModel.setRows(Collections.emptyList());
        }
        return messageJqGridModel;
    }

    // 处理增删改操作
    @RequestMapping("/handle")
    public Boolean handle(String oper, @RequestParam(name = "id") List<String> ids, LeaveMessage message) {
        try {
            if ("edit".equals(oper)) {
                if (message.getId() == null) {
                    message.setLid(Integer.parseInt(ids.get(0)));
                }
                return update(message) != null;
            } else if ("del".equals(oper)) {
                List<Integer> integers = ids.stream().map((Integer::parseInt)).collect(Collectors.toList());
                return delete(integers);
            } else if ("add".equals(oper)) {
                return add(message, null) != null;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @RequestMapping("/add")
    public LeaveMessage add(LeaveMessage leaveMessage, HttpSession session) {
        if (leaveMessage.getUid() == null) {
            User user = (User) session.getAttribute("user");
            if (user == null) {
                return null;
            }
            leaveMessage.setUid(user.getId());
        }
        System.out.println(leaveMessage);
        try {
            return leaveMessageService.add(leaveMessage);
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping("/delete")
    public Boolean delete(List<Integer> ids) {
        try {
            if (ids == null) {
                return false;
            }
            return leaveMessageService.delete(ids);
        } catch (Exception e) {
            return false;
        }
    }

    @RequestMapping("/delete_by_uid")
    public Boolean deleteByUid(Integer uid) {
        try {
            if (uid == null) {
                return false;
            }
            return leaveMessageService.deleteAllByUid(uid);
        } catch (Exception e) {
            return false;
        }
    }

    @RequestMapping("/delete_by_mid")
    public Boolean deleteByMid(Integer mid) {
        try {
            if (mid == null) {
                return false;
            }
            return leaveMessageService.deleteAllByMid(mid);
        } catch (Exception e) {
            return false;
        }
    }

    @RequestMapping("/update")
    public LeaveMessage update(LeaveMessage message) {
        try {
            if (message.getId() == null) {
                return null;
            }
            return leaveMessageService.update(message);
        } catch (Exception e) {
            return null;
        }
    }
}
