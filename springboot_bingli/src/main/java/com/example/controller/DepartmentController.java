package com.example.controller;

import com.example.common.Result;
import com.example.entity.Admin;
import com.example.entity.Department;
import com.example.entity.Doctor;
import com.example.service.DepartmentService;
import com.example.service.DoctorService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/depart")
public class DepartmentController {

    /**
     * controller里的一个用法，web项目一个接口的入口
     * 可以在此方法中再加入个url
     *
     */

    @Resource
    private DepartmentService departmentService;

    @PostMapping("/add")
    public Result add( @RequestBody Department user){
        departmentService.add(user);
        return Result.success();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Department user){
        departmentService.update(user);
        return Result.success();
    }

    @GetMapping("/search")
    public Result findBySearch(Department user){
        PageInfo<Department> info = departmentService.findBySearch(user);
        return Result.success(info);
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id){
        departmentService.delete(id);
        return Result.success();
    }
    @PutMapping("/dropBatch")
    public Result dropBatch(@RequestBody List<Department> list){
        for(Department department:list){
            departmentService.delete(department.getId());
        }
        return Result.success();
    }
    @GetMapping("/searchByDepartment/{department}")
    public Result findByDepartment(@PathVariable("department") String department){
        List<Doctor> list = departmentService.findByDepartment(department);
        return Result.success(list);
    }


}
