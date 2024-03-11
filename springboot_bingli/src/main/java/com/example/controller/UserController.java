package com.example.controller;

import com.example.common.Result;
import com.example.entity.Admin;
import com.example.entity.User;
import com.example.service.AdminService;
import com.example.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * controller里的一个用法，web项目一个接口的入口
     * 可以在此方法中再加入个url
     *
     */

    @Resource
    private UserService userService;

    @PostMapping("/add")
    public Result add( @RequestBody User user){
        userService.add(user);
        return Result.success();
    }

    @PostMapping("/update")
    public Result update(@RequestBody User user){
        userService.update(user);
        return Result.success();
    }

    @GetMapping("/search")
    public Result findBySearch(User user){
        PageInfo<User> info = userService.findBySearch(user);
        return Result.success(info);
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id){
        userService.delete(id);
        return Result.success();
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user){
        User loginUser=userService.login(user);
        return Result.success(loginUser);
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user){
        userService.add(user);
        return Result.success();
    }
    @PutMapping("/dropBatch")
    public Result dropBatch(@RequestBody List<User> list){
        for(User user:list){
            userService.delete(user.getId());
        }
        return Result.success();


    }
}
