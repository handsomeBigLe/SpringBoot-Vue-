package com.example.dao;

import com.example.entity.Department;
import com.example.entity.Doctor;
import com.example.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository

public interface DoctorDao extends Mapper<Doctor> {

    //注解的方式写sql语句
    //@Select("select * from userinfo")

    List<Doctor> findBySearch(@Param("user") Doctor user);

    @Select("select * from doctorinfo where account = #{account} or id = #{id}")
    Doctor findByIdAndAccount(@Param("account") String account,@Param("id") String id);

    @Select("select * from doctorinfo where account = #{account} and password = #{password} limit 1")
    Doctor findByAccountAndPassword(@Param("account")String account, @Param("password")String password);

    @Delete("delete from doctorinfo where id = #{id}")
    void deleteById(@Param("id") String id);

    Integer getConsultationNum(@Param("name") String name);
}

