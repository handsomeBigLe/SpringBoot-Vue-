package com.example.service;

import com.example.dao.DepartmentDao;
import com.example.dao.RecordDao;
import com.example.entity.Department;
import com.example.entity.Record;
import com.example.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.util.StringUtil;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RecordService {
    @Resource
    private RecordDao recordDao;


    public PageInfo<Record> findBySearch(Record user) {
        //开启分页查询
        PageHelper.startPage(user.getPageNum(), user.getPageSize());
        //接下来查询会自动按照分页设置查询
        List<Record> list = recordDao.findBySearch(user);
        return PageInfo.of(list);
    }

    public void add(Record user) {
        Record tempUser = recordDao.findById(user.getId());
        if (tempUser != null) {
            //用户重复
            throw new CustomException("病历号重复,请勿重新添加！");
        }
        //判定是否有空属性
        if (StringUtil.isEmpty(user.getId())
                || StringUtil.isEmpty(user.getDate())
                || StringUtil.isEmpty(user.getContent())
                || StringUtil.isEmpty(user.getDepartment())
                || StringUtil.isEmpty(user.getDoctor())
                || StringUtil.isEmpty(user.getSolution())
                || StringUtil.isEmpty(user.getPatient())
                || StringUtil.isEmpty(user.getPatientId())
        ) {
            throw new CustomException("请填写所有信息，勿留空！");
        } else {
            recordDao.insertSelective(user);
        }
    }

    public void update(Record user) {
        recordDao.updateByPrimaryKeySelective(user);
    }

    public void delete(String id) {
        recordDao.deleteById(id);
    }


}
