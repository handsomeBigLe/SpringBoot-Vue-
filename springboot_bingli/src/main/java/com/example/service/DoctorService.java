package com.example.service;

import com.example.dao.DoctorDao;
import com.example.dao.UserDao;
import com.example.entity.Department;
import com.example.entity.Doctor;
import com.example.entity.User;
import com.example.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import tk.mybatis.mapper.util.StringUtil;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DoctorService {
    @Resource
    private DoctorDao doctorDao;



    public PageInfo<Doctor> findBySearch(Doctor user) {
        //开启分页查询
        PageHelper.startPage(user.getPageNum(), user.getPageSize());
        //接下来查询会自动按照分页设置查询
        List<Doctor> list = doctorDao.findBySearch(user);
        return PageInfo.of(list);
    }

    public void add(Doctor user) {
        Doctor tempUser = doctorDao.findByIdAndAccount(user.getAccount(),user.getId());
        if (tempUser != null){
            //用户重复
            throw new CustomException("该用户已存在,请勿重新添加！");
        }
        //判定是否有空属性
        if (StringUtil.isEmpty(user.getName())
                ||StringUtil.isEmpty(user.getDepartment())
                ||StringUtil.isEmpty(user.getId())
                ||StringUtil.isEmpty(user.getSex())
                ||StringUtil.isEmpty(user.getPhone())
                ||StringUtil.isEmpty(user.getAccount())
                ||StringUtil.isEmpty(user.getPassword())){
            throw new CustomException("请填写所有信息，勿留空！");
        }else {
            doctorDao.insertSelective(user);
        }
    }

    public void update(Doctor user) {
        doctorDao.updateByPrimaryKeySelective(user);
    }

    public void delete(String id) {
        doctorDao.deleteById(id);
    }

    public Doctor login(Doctor user) {
        //1.非空判断
        if (StringUtil.isEmpty(user.getAccount())
                ||StringUtil.isEmpty(user.getPassword())){
            throw new CustomException("用户名或密码为空，请重新输入！");
        }
        //2.从数据库中根据用户名及密码查询对应管理员信息
        Doctor tempUser =  doctorDao.findByAccountAndPassword(user.getAccount(),user.getPassword());

        if (tempUser == null) {
            throw new CustomException("用户名或密码错误，请重新输入！");
        }
        return tempUser;
    }


    public Integer getConsultationNum(String name) {
        return doctorDao.getConsultationNum(name);
        //    return 10;
    }
}
