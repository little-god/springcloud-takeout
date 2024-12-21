package com.meng.cloud.common.feign;

import com.meng.cloud.common.entity.Menu;
import com.meng.cloud.common.entity.Type;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("takeout-menu")
public interface MenuFeign {

    @GetMapping("/menu/findAll/{index}/{limit}")
    public List<Menu> findAll(@PathVariable("index") int index, @PathVariable("limit") int limit);

    @DeleteMapping("/menu/deleteById/{id}")
    void deleteById(@PathVariable("id") long id);

    @GetMapping("/menu/findTypes")
    List<Type> findTypes();

    @PostMapping("/menu/save")
    void save(@RequestBody Menu menu);

    @GetMapping("/menu/findById/{id}")
    Menu findById(@PathVariable("id") long id);

    @PostMapping("/menu/update")
    void update(Menu menu);
}
