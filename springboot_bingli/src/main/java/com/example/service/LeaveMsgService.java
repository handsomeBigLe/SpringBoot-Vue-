package com.example.service;

import com.example.dao.LeaveMsgDao;
import com.example.dao.RecordDao;
import com.example.entity.LeaveMsg;
import com.example.entity.Record;
import com.example.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.util.StringUtil;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LeaveMsgService {
    @Resource
    private LeaveMsgDao leaveMsgDao;


    public PageInfo<LeaveMsg> findBySearch(LeaveMsg user) {
        //开启分页查询
        PageHelper.startPage(user.getPageNum(), user.getPageSize());
        //接下来查询会自动按照分页设置查询
        List<LeaveMsg> list = leaveMsgDao.findBySearch(user);
        return PageInfo.of(list);
    }


    public void update(LeaveMsg user) {
        leaveMsgDao.updateByPrimaryKeySelective(user);
    }

    public void delete(String id) {
        leaveMsgDao.deleteById(id);
    }


}
