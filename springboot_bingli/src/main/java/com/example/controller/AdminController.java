package com.example.controller;

import com.example.common.Result;
import com.example.entity.Admin;
import com.example.entity.User;
import com.example.service.AdminService;
import com.example.service.UserService;
import com.github.pagehelper.PageInfo;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {

    /**
     * controller里的一个用法，web项目一个接口的入口
     * 可以在此方法中再加入个url
     *
     */

    @Resource
    private AdminService adminService;


    @RequestMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        SpecCaptcha captcha = new SpecCaptcha(135,33,4);
        captcha.setCharType(Captcha.TYPE_ONLY_UPPER);
        CaptchaUtil.out(captcha,request,response);
    }

    @PostMapping("/add")
    public Result add( @RequestBody Admin admin){
        adminService.add(admin);
        return Result.success();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Admin admin){
        adminService.update(admin);
        return Result.success();
    }

    @GetMapping("/search")
    public Result findBySearch(Admin admin){
        PageInfo<Admin> info = adminService.findBySearch(admin);
        return Result.success(info);
    }
    @DeleteMapping("/{account}")
    public Result delete(@PathVariable String account){
        adminService.delete(account);
        return Result.success();
    }

    @PostMapping("/login")
    public Result login(@RequestBody Admin admin){
        Admin loginUser=adminService.login(admin);
        return Result.success(loginUser);
    }

    @PutMapping("/dropBatch")
    public Result dropBatch(@RequestBody List<Admin> list){
        for(Admin admin:list){
            adminService.delete(admin.getAccount());
        }
        return Result.success();


    }



}
