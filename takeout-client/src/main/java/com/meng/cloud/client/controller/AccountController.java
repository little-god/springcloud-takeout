package com.meng.cloud.client.controller;


import com.meng.cloud.common.entity.Account;
import com.meng.cloud.common.entity.Admin;
import com.meng.cloud.common.entity.User;
import com.meng.cloud.common.feign.AccountFeign;
import com.meng.cloud.client.utils.ReflectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import jakarta.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountFeign accountFeign;

    /**
     * 用户登录接口
     * @param username
     * @param password
     * @param type
     * @param session
     * @return
     */
    @PostMapping("/login")
    @CircuitBreaker(name = "takeout-account", fallbackMethod = "loginCircuitFallback")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("type") String type, HttpSession session) {
        Account account = accountFeign.login(username, password, type);
        String target = null;
        if (account == null) {
            target = "redirect:/view/redirect/login";
        } else {
            switch (type) {
                case "user":
                    User user = convertUser(account);
                    session.setAttribute("user", user);
                    target = "redirect:/view/redirect/index";
                    break;
                case "admin":
                    Admin admin = convertAdmin(account);
                    session.setAttribute("admin", admin);
                    target = "redirect:/view/redirect/main";
                    break;
            }
        }
        return target;
    }

    public String loginCircuitFallback(String username, String password, String type, HttpSession session,Throwable t) {
        return "登录系统繁忙，请稍后再登录！";
    }
    /**
     * 用户登出
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "login";
    }

    private User convertUser(Account account) {
        User user = new User();
        user.setUsername(ReflectUtils.getFieldValue(account, "username") + "");
        user.setPassword(ReflectUtils.getFieldValue(account, "password") + "");
        user.setGender(ReflectUtils.getFieldValue(account, "gender") + "");
        user.setId((long) (ReflectUtils.getFieldValue(account, "id")));
        user.setNickname(ReflectUtils.getFieldValue(account, "nickname") + "");
        user.setRegisterdate((Date) (ReflectUtils.getFieldValue(account, "registerdate")));
        user.setTelephone(ReflectUtils.getFieldValue(account, "telephone") + "");
        return user;
    }

    private Admin convertAdmin(Account account) {
        Admin admin = new Admin();
        admin.setUsername(ReflectUtils.getFieldValue(account, "username") + "");
        admin.setPassword(ReflectUtils.getFieldValue(account, "password") + "");
        admin.setId((long) (ReflectUtils.getFieldValue(account, "id")));
        return admin;
    }
}

