package com.example.service;

import com.example.dao.DepartmentDao;
import com.example.dao.DoctorDao;
import com.example.entity.Department;
import com.example.entity.Doctor;
import com.example.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.util.StringUtil;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DepartmentService {
    @Resource
    private DepartmentDao departmentDao;



    public PageInfo<Department> findBySearch(Department user) {
        //开启分页查询
        PageHelper.startPage(user.getPageNum(), user.getPageSize());
        //接下来查询会自动按照分页设置查询
        List<Department> list = departmentDao.findBySearch(user);
        return PageInfo.of(list);
    }

    public void add(Department user) {
        Department tempUser = departmentDao.findByIdAndName(user.getId(),user.getName());
        if (tempUser != null){
            //用户重复
            throw new CustomException("该科室已存在,请勿重新添加！");
        }
        //判定是否有空属性
        if (StringUtil.isEmpty(user.getName())
                ||StringUtil.isEmpty(user.getId())
                ||StringUtil.isEmpty(user.getIntroduce())
                ){
            throw new CustomException("请填写所有信息，勿留空！");
        }else {
            departmentDao.insertSelective(user);
        }
    }

    public void update(Department user) {
        departmentDao.updateByPrimaryKeySelective(user);
    }

    public void delete(String id) {
        departmentDao.deleteById(id);
    }

    public List<Doctor> findByDepartment( String department) {
        return departmentDao.findByDepartment(department);
    }
}
