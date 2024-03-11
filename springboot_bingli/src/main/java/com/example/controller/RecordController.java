package com.example.controller;

import cn.hutool.log.Log;
import com.example.common.Result;
import com.example.entity.Department;
import com.example.entity.Record;
import com.example.service.DepartmentService;
import com.example.service.RecordService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/record")
public class RecordController {

    /**
     * controller里的一个用法，web项目一个接口的入口
     * 可以在此方法中再加入个url
     *
     */

    @Resource
    private RecordService recordService;

    @PostMapping("/add")
    public Result add( @RequestBody Record user){
        recordService.add(user);
        return Result.success();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Record user){
        recordService.update(user);
        return Result.success();
    }

    @GetMapping("/search")
    public Result findBySearch(Record user){
        PageInfo<Record> info = recordService.findBySearch(user);
        return Result.success(info);
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id){
        recordService.delete(id);
        return Result.success();
    }
    @PutMapping("/dropBatch")
    public Result dropBatch(@RequestBody List<Record> list){
        for(Record record:list){
            recordService.delete(record.getId());
        }
        return Result.success();
    }


}
