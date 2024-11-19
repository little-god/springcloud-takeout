package com.meng.cloud.client.feign;

import com.meng.cloud.client.entity.Menu;
import com.meng.cloud.client.entity.Type;
import com.meng.cloud.client.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("takeout-user")
public interface UserFeign {

    @GetMapping("/user/findAll/{index}/{limit}")
    public List<User> findAll(@PathVariable("index") int index, @PathVariable("limit") int limit);

    @DeleteMapping("/user/deleteById/{id}")
    void deleteById(@PathVariable("id") long id);

    @GetMapping("/user/findTypes")
    List<Type> findTypes();

    @PostMapping("/user/save")
    void save(@RequestBody User user);

    @GetMapping("/user/findById/{id}")
    Menu findById(@PathVariable("id") long id);

    @PostMapping("/user/update")
    void update(User user);
}
