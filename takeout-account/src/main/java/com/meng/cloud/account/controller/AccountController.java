package com.meng.cloud.account.controller;

import com.meng.cloud.common.entity.Account;
import com.meng.cloud.common.feign.AdminFeign;
import com.meng.cloud.common.feign.UserFeign;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Value("${server.port}")
    private String port;

    @Resource
    private AdminFeign adminFeignApi;

    @Resource
    private UserFeign userFeignApi;

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
                account = userFeignApi.login(username, password, type);
                break;
            case "admin":
                account = adminFeignApi.login(username, password, type);
                break;
        }
        return account;
    }
}
