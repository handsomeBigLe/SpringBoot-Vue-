package com.example.dao;

import com.example.entity.Department;
import com.example.entity.Doctor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository

public interface DepartmentDao extends Mapper<Department> {

    //注解的方式写sql语句
    //@Select("select * from userinfo")

    List<Department> findBySearch(@Param("user") Department user);

    @Select("select * from departmentinfo where id = #{id} or name = #{name}")
    Department findByIdAndName(@Param("id") String id,@Param("name") String name);


    @Delete("delete from departmentinfo where id = #{id}")
    void deleteById(@Param("id") String id);

    @Select("select * from doctorinfo where department like #{department}")
    List<Doctor> findByDepartment(@Param("department") String department);
}

