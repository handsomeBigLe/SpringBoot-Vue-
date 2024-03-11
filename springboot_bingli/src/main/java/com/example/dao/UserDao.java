package com.example.dao;

import com.example.entity.Admin;
import com.example.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository

public interface UserDao extends Mapper<User> {

    //注解的方式写sql语句
    //@Select("select * from userinfo")

    List<User> findBySearch(@Param("users") User user);

    @Select("select * from userinfo where account = #{account} or id = #{id}")
    User findByIdAndAccount(@Param("account") String account,@Param("id") String id);

    @Select("select * from userinfo where account = #{account} and password = #{password} limit 1")
    User findByAccountAndPassword(@Param("account")String account, @Param("password")String password);
}

