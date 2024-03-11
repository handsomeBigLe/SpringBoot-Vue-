package com.example.controller;

import com.example.common.Result;
import com.example.entity.LeaveMsg;
import com.example.entity.Record;
import com.example.service.LeaveMsgService;
import com.example.service.RecordService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/leaveMsg")
public class LeaveMsgController {

    /**
     * controller里的一个用法，web项目一个接口的入口
     * 可以在此方法中再加入个url
     *
     */

    @Resource
    private LeaveMsgService leaveMsgService;


    @PostMapping("/update")
    public Result update(@RequestBody LeaveMsg user){
        leaveMsgService.update(user);
        return Result.success();
    }

    @GetMapping("/search")
    public Result findBySearch(LeaveMsg user){
        PageInfo<LeaveMsg> info = leaveMsgService.findBySearch(user);
        return Result.success(info);
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id){
        leaveMsgService.delete(id);
        return Result.success();
    }
    @PutMapping("/dropBatch")
    public Result dropBatch(@RequestBody List<LeaveMsg> list){
        for(LeaveMsg leaveMsg:list){
            leaveMsgService.delete(leaveMsg.getId());
        }
        return Result.success();
    }


}
