package com.example.service;

import com.example.dao.AdminDao;
import com.example.dao.UserDao;
import com.example.entity.Admin;
import com.example.entity.User;
import com.example.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.util.StringUtil;

import javax.annotation.Resource;
import javax.persistence.Id;
import java.util.List;

@Service
public class UserService {
    @Resource
    private UserDao userDao;


    /*public List<Admin> findBySearch(Admin admin) {
        return adminDao.findBySearch(admin);
    }*/

    public PageInfo<User> findBySearch(User user) {
        //开启分页查询
        PageHelper.startPage(user.getPageNum(), user.getPageSize());
        //接下来查询会自动按照分页设置查询
        List<User> list = userDao.findBySearch(user);
        return PageInfo.of(list);
    }

    public void add(User user) {
        User tempUser = userDao.findByIdAndAccount(user.getAccount(),user.getId());
        if (tempUser != null){
            //用户重复
            throw new CustomException("该用户已存在,请勿重新添加！");
        }
        //判定是否有空属性
        if (StringUtil.isEmpty(user.getName())
                ||StringUtil.isEmpty(user.getId())
                ||StringUtil.isEmpty(user.getSex())
                ||StringUtil.isEmpty(user.getPhone())
                ||StringUtil.isEmpty(user.getAccount())
                ||StringUtil.isEmpty(user.getPassword())){
            throw new CustomException("请填写所有信息，勿留空！");
        }else {
            userDao.insertSelective(user);
        }
    }

    public void update(User user) {
        userDao.updateByPrimaryKeySelective(user);
    }

    public void delete(String id) {
        userDao.deleteByPrimaryKey(id);
    }

    public User login(User user) {
        //1.非空判断
        if (StringUtil.isEmpty(user.getAccount())
                ||StringUtil.isEmpty(user.getPassword())){
            throw new CustomException("用户名或密码为空，请重新输入！");
        }
        //2.从数据库中根据用户名及密码查询对应管理员信息
        User tempUser =  userDao.findByAccountAndPassword(user.getAccount(),user.getPassword());

        if (tempUser == null) {
            throw new CustomException("用户名或密码错误，请重新输入！");
        }

        return tempUser;
    }
}
