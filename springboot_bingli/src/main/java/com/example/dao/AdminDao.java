package com.example.dao;

import com.example.entity.Admin;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository

public interface AdminDao extends Mapper<Admin> {

    //注解的方式写sql语句
    //@Select("select * from userinfo")

    List<Admin> findBySearch(@Param("admins") Admin admin);

    @Select("select * from admininfo where account = #{account} limit 1")
    Admin findByAccount(@Param("account") String account);

    @Select("select * from admininfo where account = #{account} and password = #{password} limit 1")
    Admin findByAccountAndPassword(@Param("account")String account, @Param("password")String password);
}

