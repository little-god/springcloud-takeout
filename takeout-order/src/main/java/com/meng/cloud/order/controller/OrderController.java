package com.meng.cloud.order.controller;

import com.meng.cloud.common.entity.Menu;
import com.meng.cloud.common.entity.Order;
import com.meng.cloud.common.entity.OrderVO;
import com.meng.cloud.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/index")
    public String index(){
        return "订单服务端口号："+ this.port;
    }

    @Autowired
    private OrderRepository orderRepository;

    /**
     * 订单添加
     * @param order
     */
    @PostMapping("/save")
    public void save(@RequestBody Order order){
        Menu menu = order.getMenu();
        Long menuId = menu.getId();
        if(menuId == 10){
            throw new RuntimeException("无法下单西葫芦虾仁蒸饺");
        }
        if(menuId == 12){
            try {
                TimeUnit.SECONDS.sleep(5);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        orderRepository.save(order);
    }

    /**
     * 通过用户id查询
     * @param uid
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/findAllByUid/{uid}/{page}/{limit}")
    public OrderVO findAllByUid(@PathVariable("uid") long uid, @PathVariable("page") int page, @PathVariable("limit") int limit){
        OrderVO orderVO = new OrderVO();
        orderVO.setCode(0);
        orderVO.setMsg("");
        orderVO.setCount(orderRepository.countByUid(uid));
        orderVO.setData(orderRepository.findAllByUid(uid,(page-1)*limit,limit));
        return orderVO;
    }

    /**
     * 删除
     * @param mid
     */
    @DeleteMapping("/deleteByMid/{mid}")
    public void deleteByMid(@PathVariable("mid") long mid){
        orderRepository.deleteByMid(mid);
    }

    /**
     * 通过用户id删除
     * @param uid
     */
    @DeleteMapping("/deleteByUid/{uid}")
    public void deleteByUid(@PathVariable("uid") long uid){
        orderRepository.deleteByUid(uid);
    }

    /**
     * 通过状态查询
     * @param state
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/findAllByState/{state}/{page}/{limit}")
    public OrderVO findAllByState(@PathVariable("state") int state, @PathVariable("page") int page, @PathVariable("limit") int limit){
        OrderVO orderVO = new OrderVO();
        orderVO.setCode(0);
        orderVO.setMsg("");
        orderVO.setCount(orderRepository.countByState(0));
        orderVO.setData(orderRepository.findAllByState(0,(page-1)*limit,limit));
        return orderVO;
    }

    /**
     * 修改订单
     * @param id
     * @param state
     * @param aid
     */
    @PutMapping("/updateState/{id}/{state}/{aid}")
    public void updateState(@PathVariable("id") long id, @PathVariable("state") int state, @PathVariable("aid") long aid){
        orderRepository.updateState(id,aid,state);
    }
}
