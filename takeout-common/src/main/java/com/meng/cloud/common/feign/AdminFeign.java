package com.meng.cloud.common.feign;


import com.meng.cloud.common.entity.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "takeout-admin")
public interface AdminFeign {
    @GetMapping("/admin/login/{username}/{password}/{type}")
    public Account login(@PathVariable("username") String username, @PathVariable("password") String password, @PathVariable("type") String type);
}
