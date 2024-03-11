package com.example.service;

import com.example.common.JwtTokenUtils;
import com.example.dao.AdminDao;
import com.example.entity.Admin;
import com.example.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.util.StringUtil;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminService {
    @Resource
    private AdminDao adminDao;


    /*public List<Admin> findBySearch(Admin admin) {
        return adminDao.findBySearch(admin);
    }*/

    public PageInfo<Admin> findBySearch(Admin admin) {
        //开启分页查询
        PageHelper.startPage(admin.getPageNum(), admin.getPageSize());
        //接下来查询会自动按照分页设置查询
        List<Admin> list = adminDao.findBySearch(admin);
        return PageInfo.of(list);
    }
    public Admin findById(String account){
        return adminDao.findByAccount(account);
    }

    public void add(Admin admin) {
        Admin user = adminDao.findByAccount(admin.getAccount());
        if (user != null){
            //用户重复
            throw new CustomException("该用户已存在,请勿重新添加！");
        }
        if (StringUtil.isEmpty(admin.getName())
                ||StringUtil.isEmpty(admin.getAccount())
                ||StringUtil.isEmpty(admin.getPassword())){
            throw new CustomException("用户名、密码或姓名为空，请重新输入！");
        }else {
            adminDao.insertSelective(admin);
        }
    }

    public void update(Admin admin) {
        adminDao.updateByPrimaryKeySelective(admin);
    }

    public void delete(String account) {
        adminDao.deleteByPrimaryKey(account);
    }

    public Admin login(Admin admin) {
        //1.非空判断
        if (StringUtil.isEmpty(admin.getAccount())
                ||StringUtil.isEmpty(admin.getPassword())){
            throw new CustomException("用户名或密码为空，请重新输入！");
        }
        //2.从数据库中根据用户名及密码查询对应管理员信息
        Admin user =  adminDao.findByAccountAndPassword(admin.getAccount(),admin.getPassword());

        if (user == null) {
            throw new CustomException("用户名或密码错误，请重新输入！");
        }

        //生成该用户对应的token并返回到前端
        String token = JwtTokenUtils.genToken(user.getAccount(),user.getPassword());
        user.setToken(token);
        return user;
    }
}
