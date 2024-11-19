package com.meng.cloud.client.controller;

import com.meng.cloud.client.entity.User;
import com.meng.cloud.client.entity.UserVO;
import com.meng.cloud.client.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/user")
public class UserClientController {

    @Autowired
    UserFeign userFeign;

    /**
     * 分页查询列表
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/findAll")
    @ResponseBody
    public UserVO findAll(@RequestParam("page") int page, @RequestParam("limit") int limit){
        return new UserVO(0,"",1000, userFeign.findAll(page, limit));
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @GetMapping("/deleteById/{id}")
    public String deleteById(@PathVariable("id") long id){
        userFeign.deleteById(id);
        return "user_manage";
    }

    /**
     * 添加
     * @param user
     * @return
     */
    @PostMapping("/save")
    public String save(User user){
        userFeign.save(user);
        return "user_manage";
    }

    /**
     * 查询详情
     * @param id
     * @return
     */
    @GetMapping("/findById/{id}")
    public ModelAndView findById(@PathVariable("id") long id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", userFeign.findById(id));
        modelAndView.setViewName("user_update");
        return modelAndView;
    }

    /**
     * 修改
     * @param user
     * @return
     */
    @PostMapping("/update")
    public String update(User user){
        userFeign.update(user);
        return "user_manage";
    }
}
