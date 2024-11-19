package com.meng.cloud.user.controller;

import com.meng.cloud.user.entity.User;
import com.meng.cloud.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Value("${server.port}")
    private String port;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/index")
    public String index(){
        return "用户服务端口号："+ this.port;
    }

    /**
     * 分页查询
     * @param index
     * @param limit
     * @return
     */
    @GetMapping("/findAll/{index}/{limit}")
    public List<User> findAll(@PathVariable("index") int index, @PathVariable("limit") int limit) {
        index = (index - 1) * limit;
        return userRepository.findAll(index, limit);
    }

    /**
     * 删除
     * @param id
     */
    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") long id) {
        userRepository.deleteById(id);
    }

    /**
     * 添加
     * @param user
     */
    @PostMapping("/save")
    void save(@RequestBody User user) {
        user.setRegisterdate(new Date());
        userRepository.save(user);
    }

    /**
     * 查询详情
     * @param id
     * @return
     */
    @GetMapping("/findById/{id}")
    User findById(@PathVariable("id") long id) {
        return userRepository.findById(id);
    }

    /**
     * 更新
     * @param user
     */
    @PostMapping("/update")
    void update(@RequestBody User user) {
        userRepository.update(user);
    }
}
