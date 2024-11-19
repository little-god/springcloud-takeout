package com.meng.cloud.client.controller;

import com.meng.cloud.client.feign.MenuFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/view")
public class ViewClientController {

    @Autowired
    MenuFeign menuFeign;

    /**
     * 重定向到某页面
     * @param index
     * @return
     */
    @GetMapping("/redirect/{index}")
    public String findAll(@PathVariable("index") String index){
        return index;
    }
}
