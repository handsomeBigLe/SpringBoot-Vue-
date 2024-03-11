package com.example.common;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.entity.Admin;
import com.example.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class JwtTokenUtils {
    private static AdminService staticAdminService;
    private static final Logger log = LoggerFactory.getLogger(JwtTokenUtils.class);

    @Resource
    private AdminService adminService;

    @PostConstruct
    public void setUserService() {
        staticAdminService = adminService;
    }

    //生成token
    public static String genToken(String adminId, String sign) {
        //将userId保存到token内作为载荷)
        return JWT.create().withAudience(adminId)
                //2小时后过期
                .withExpiresAt(DateUtil.offsetHour(new Date(), 2))
                //以password作为token秘钥
                .sign(Algorithm.HMAC256(sign));
    }

    //获取当前登录的用户信息
    public static Admin getCurrentAdmin() {
        String token = null;
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            token = request.getHeader("token");
            if (StrUtil.isBlank(token)) {
                token = request.getParameter("token");
            }
            if (StrUtil.isBlank(token)) {
                log.error("获取当前登录的token失败，token：{}", token);
                return null;
            }
            //解析token
            String adminId = JWT.decode(token).getAudience().get(0);
            return staticAdminService.findById(adminId);

        } catch (Exception e) {
            log.error("获取当前登录的管理员信息失败,token={}", token, e);
            return null;
        }
    }

}
