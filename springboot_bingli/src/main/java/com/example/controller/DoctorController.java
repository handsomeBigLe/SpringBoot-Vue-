package com.example.controller;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.example.common.Result;
import com.example.entity.Department;
import com.example.entity.Doctor;
import com.example.entity.Record;
import com.example.service.DoctorService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.util.StringUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/doctor")
public class DoctorController {

    /**
     * controller里的一个用法，web项目一个接口的入口
     * 可以在此方法中再加入个url
     *
     */

    @Resource
    private DoctorService doctorService;

    @PostMapping("/add")
    public Result add( @RequestBody Doctor user){
        doctorService.add(user);
        return Result.success();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Doctor user){
        doctorService.update(user);
        return Result.success();
    }

    @GetMapping("/search")
    public Result findBySearch(Doctor user){
        PageInfo<Doctor> info = doctorService.findBySearch(user);
        List<Doctor> doctors = info.getList();
        Integer consultationNum = 0;
        for(Doctor doctor :doctors){
            consultationNum = doctorService.getConsultationNum(doctor.getName());
            if(consultationNum == null){
                consultationNum=0;
            }
            doctor.setConsultationNum(consultationNum);
        }
        return Result.success(info);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id){
        doctorService.delete(id);
        return Result.success();
    }

    @PostMapping("/login")
    public Result login(@RequestBody Doctor user){
        Doctor loginUser=doctorService.login(user);
        return Result.success(loginUser);
    }
    @PutMapping("/dropBatch")
    public Result dropBatch(@RequestBody List<Doctor> list){
        for(Doctor doctor:list){
            doctorService.delete(doctor.getId());
        }
        return Result.success();


    }
    public Integer getConsultationNum(String name) {
        return doctorService.getConsultationNum(name);
    }

}
