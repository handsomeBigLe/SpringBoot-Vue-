package com.example.dao;

import com.example.entity.LeaveMsg;
import com.example.entity.Record;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository

public interface LeaveMsgDao extends Mapper<LeaveMsg> {

    //注解的方式写sql语句
    //@Select("select * from userinfo")

    List<LeaveMsg> findBySearch(@Param("user") LeaveMsg user);


    @Delete("delete from leavemessageinfo where id = #{id}")
    void deleteById(@Param("id") String id);



}

