package com.meng.cloud.admin.controller;

import com.meng.cloud.common.entity.Account;
import com.meng.cloud.admin.repository.AdminRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Value("${server.port}")
    private String port;

    @Resource
    AdminRepository adminRepository;

    @GetMapping("/index")
    public String index() {
        return "管理员服务端口号为： " + this.port;
    }

    /**
     * 管理员登录接口
     * @param username
     * @param password
     * @return
     */
    @GetMapping("/login/{username}/{password}/{type}")
    public Account login(@PathVariable("username") String username, @PathVariable("password") String password, @PathVariable("type") String type) {
        Account account = new Account();
        account = adminRepository.login(username, password);
        return account;
    }
}
