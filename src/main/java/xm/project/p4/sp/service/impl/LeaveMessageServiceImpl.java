package xm.project.p4.sp.service.impl;

import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import xm.project.p4.sp.dao.LeaveMessageDao;
import xm.project.p4.sp.model.LeaveMessage;
import xm.project.p4.sp.service.LeaveMessageService;
import xm.project.p4.sp.util.PagingUtil;

import javax.transaction.Transactional;
import java.beans.BeanProperty;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaveMessageServiceImpl implements LeaveMessageService {

    private final LeaveMessageDao leaveMessageDao;

    public LeaveMessageServiceImpl(LeaveMessageDao leaveMessageDao) {
        this.leaveMessageDao = leaveMessageDao;
    }

    @Override
    public int totalPageCount(int pageSize) {
        int count = (int) leaveMessageDao.count();
        return PagingUtil.totalPageCount(count, pageSize);
    }

    @Override
    public List<LeaveMessage> all(int page, int size) {
        PagingUtil.checkPageAndSize(page, size);
        return leaveMessageDao.findAll(PageRequest.of(page, size)).toList();
    }

    @Override
    public List<LeaveMessage> allByUid(Integer uid, int page, int size) {
        PagingUtil.checkPageAndSize(page, size);
        return leaveMessageDao.findByUid(uid, PageRequest.of(page, size));
    }

    @Override
    public List<LeaveMessage> allByMid(Integer mid, int page, int size) {
        PagingUtil.checkPageAndSize(page, size);
        return leaveMessageDao.findByMid(mid, PageRequest.of(page, size));
    }

    @Override
    public LeaveMessage add(LeaveMessage leaveMessage) {
        if (leaveMessage == null) {
            return null;
        }
        return leaveMessageDao.save(leaveMessage);
    }

    @Override
    public Boolean delete(Integer id) {
        if (id == null) {
            return false;
        }
        try {
            leaveMessageDao.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    @Override
    public Boolean delete(List<Integer> ids) {
        if (ids == null || ids.isEmpty()) {
            return false;
        }
        try {
            List<LeaveMessage> messages = ids.stream().map(id -> {
                LeaveMessage message = new LeaveMessage();
                message.setLid(id);
                return message;
            }).collect(Collectors.toList());
            leaveMessageDao.deleteAll(messages);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    @Override
    public Boolean deleteAllByUid(Integer uid) {
        if (uid == null) {
            return false;
        }
        try {
            leaveMessageDao.deleteByUid(uid);
            return true;
        } catch (Exception e) {
            System.out.println("哎呀, 糟糕啦, 这里是LeaveMessageServiceImpl deleteAllByUid, 出现异常了...");
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Transactional
    @Override
    public Boolean deleteAllByMid(Integer mid) {
        if (mid == null) {
            return false;
        }
        try {
            leaveMessageDao.deleteByMid(mid);
            return true;
        } catch (Exception e) {
            System.out.println("哎呀, 糟糕啦, 这里是LeaveMessageServiceImpl deleteAllByMid, 出现异常了...");
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public LeaveMessage update(LeaveMessage message) {
        LeaveMessage origin = leaveMessageDao.findById(message.getId()).orElse(null);
        if (origin == null) {
            return null;
        }
        if (message.getId() != null) {
            origin.setLid(message.getId());
        }
        if (message.getUid() != null) {
            origin.setUid(message.getUid());
        }
        if (message.getMid() != null) {
            origin.setMid(message.getMid());
        }
        if (message.getMsg() != null) {
            origin.setMsg(message.getMsg());
        }
        return leaveMessageDao.save(origin);
    }
}
