package com.meng.cloud.account.controller;

import com.meng.cloud.account.entity.Account;
import com.meng.cloud.account.repository.AdminRepository;
import com.meng.cloud.account.repository.UserRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wench
 * @Description:
 * @Date: create in 2020/12/26 13:30
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Value("${server.port}")
    private String port;

    @Resource
    UserRepository userRepository;

    @Resource
    AdminRepository adminRepository;

    @GetMapping("/index")
    public String index() {
        return "账户服务端口号为： " + this.port;
    }

    /**
     * 登录接口
     * @param username
     * @param password
     * @param type
     * @return
     */
    @GetMapping("/login/{username}/{password}/{type}")
    public Account login(@PathVariable("username") String username, @PathVariable("password") String password, @PathVariable("type") String type) {
        Account account = new Account();
        switch (type) {
            case "user":
                account = userRepository.login(username, password);
                break;
            case "admin":
                account = adminRepository.login(username, password);
                break;
        }
        return account;
    }
}
