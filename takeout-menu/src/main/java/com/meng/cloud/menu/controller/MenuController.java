package com.meng.cloud.menu.controller;

import com.meng.cloud.menu.entity.Menu;
import com.meng.cloud.menu.entity.Type;
import com.meng.cloud.menu.repository.MenuRepository;
import com.meng.cloud.menu.repository.TypeRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Value("${server.port}")
    private String port;

    @Resource
    MenuRepository menuRepository;

    @Resource
    TypeRepository typeRepository;

    @GetMapping("/index")
    public String index() {
        return "菜品端口号为：" + this.port;
    }

    @GetMapping("/findAll/{index}/{limit}")
    public List<Menu> findAll(@PathVariable("index") int index, @PathVariable("limit") int limit) {
        index = (index - 1) * limit;
        return menuRepository.findAll(index, limit);
    }

    @GetMapping("/findTypes")
    public List<Type> findTypes() {
        return typeRepository.findAll();
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") long id) {
        menuRepository.deleteById(id);
    }

    @PostMapping("/save")
    void save(@RequestBody Menu menu) {
        menuRepository.save(menu);
    }

    @GetMapping("/findById/{id}")
    Menu findById(@PathVariable("id") long id) {
        menuRepository.findById(id);
        return menuRepository.findById(id);
    }

    @PostMapping("/update")
    void update(@RequestBody Menu menu) {
        menuRepository.update(menu);
    }
}
