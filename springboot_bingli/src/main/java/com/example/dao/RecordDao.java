package com.example.dao;

import com.example.entity.Department;
import com.example.entity.Record;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository

public interface RecordDao extends Mapper<Record> {

    //注解的方式写sql语句
    //@Select("select * from userinfo")

    List<Record> findBySearch(@Param("user") Record user);

    @Select("select * from recordinfo where id = #{id}")
    Record findById(@Param("id") String id);


    @Delete("delete from recordinfo where id = #{id}")
    void deleteById(@Param("id") String id);



}

