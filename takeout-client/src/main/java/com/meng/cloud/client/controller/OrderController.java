package com.meng.cloud.client.controller;


import cn.hutool.core.date.DateUtil;
import com.meng.cloud.common.entity.*;
import com.meng.cloud.common.feign.OrderFeign;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderFeign orderFeign;

    /**
     * 订单保存接口
     * @param mid
     * @param session
     * @return
     */
    @GetMapping("/save/{mid}")
    @CircuitBreaker(name = "takeout-order", fallbackMethod = "ordersaveCircuitFallback")
    public String save(@PathVariable("mid") long mid, HttpSession session){
        User user = (User) session.getAttribute("user");
        Order order = new Order();
        Menu menu = new Menu();
        menu.setId(mid);
        order.setUser(user);
        order.setMenu(menu);
        order.setDate(new Date());
        try{
            System.out.println("调用开始-----:"+DateUtil.now());
            orderFeign.save(order);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("调用结束-----:"+ DateUtil.now());
        return "redirect:/view/redirect/index";
    }

    public String ordersaveCircuitFallback(long mid, HttpSession session, Throwable t) {
        t.printStackTrace();
        return "redirect:/view/redirect/orderSaveCircuitFallback";
    }

    /**
     * 通过用户查询
     * @param page
     * @param limit
     * @param session
     * @return
     */
    @GetMapping("/findAllByUid")
    @ResponseBody
    public OrderVO findAllByUid(@RequestParam("page") int page, @RequestParam("limit") int limit, HttpSession session){
        User user = (User) session.getAttribute("user");
        return orderFeign.findAllByUid(user.getId(), page, limit);
    }

    /**
     * 通过订单查询
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/findAllByState")
    @ResponseBody
    public OrderVO findAllByState(@RequestParam("page") int page, @RequestParam("limit") int limit){
        return orderFeign.findAllByState(0, page, limit);
    }

    /**
     * 更新
     * @param id
     * @param state
     * @param session
     * @return
     */
    @GetMapping("/updateState/{id}/{state}")
    public String updateState(@PathVariable("id") long id,@PathVariable("state") int state,HttpSession session){
        Admin admin = (Admin) session.getAttribute("admin");
        orderFeign.updateState(id,state,admin.getId());
        return "redirect:/view/redirect/order_handler";
    }
}
