package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.carService;
import com.example.demo.service.orderService;
import com.example.demo.service.user_infoService;
import com.example.demo.Entity.order_;
import com.example.demo.Entity.security;
import com.example.demo.Entity.car;
import com.example.demo.Entity.user_info;

@RequestMapping("/order")
@RestController
public class orderController {
    @Autowired
    orderService orService;
    @Autowired
    user_infoService uiService;
    @Autowired
    carService cService;

    @GetMapping("/GetSecurity")
    public List<security> GetSecurity(){
        return orService.GetAllSecurity();
    }

    @GetMapping("/GetSecurityById")
    public List<security> GetSecurityById(@RequestBody security s){
        return orService.GetSecurityById(s.getSecurity_id());
    }

    @PostMapping("/GetOrderList")
    public List<order_> GetOrderList(@RequestBody order_ order){
        if(order.getUser_id() == null)
        {
            return null;
        }
        return orService.GetUserOrder(order.getUser_id());
    }

    @GetMapping("/GetOrderInfo")
    public List<order_> GetOrderInfo(@RequestBody order_ order){
        if(order.getOrder_id() == null)
        {
            return null;
        }
        return orService.GetOrderById(order.getOrder_id());
    }

    @GetMapping("/GetAllOrder")
    public List<order_> GetAllOrder(){
        return orService.GetAllOrder(0, 50);
    }

    @PostMapping("/Search_car")
    public List<car> Search_car(@RequestBody order_ order){
        return cService.getCarInfoById(order.getOrder_id());
    }

    @PostMapping("/Search_carID")
    public int Search_carID(@RequestBody order_ order){
        List<order_> order1 = orService.GetOrderById(order.getOrder_id());
        return order1.get(0).getCar_id();
    }

    @GetMapping("/Search_User")
    public List<user_info> Search_User(@RequestBody order_ order){
        return uiService.getUserInfoByid(order.getUser_id());
    }

    @PostMapping("/create")
    public Integer create(@RequestBody order_ order){
        if(order.getRent_price() == null || order.getDeposit_price() == null || order.getTotal_price() == null)
        {
            return 500;
        }
        if(order.getUser_id() == null || order.getCar_id() == null){
            return 500;
        }
        if(order.getBegintime() == null || order.getEndtime() == null)
        {
            return 500;
        }
        if(order.getState() == null)
        {
            order.setState(0);
        }
        return orService.createOrder(order);
    }

    @PostMapping("/cancel")
    public Integer cancel(@RequestBody order_ order){
        if(order.getOrder_id() == null)
        {
            return 500;
        }
        return orService.modifyOrderState(order.getOrder_id(), 4);
    }

    @PostMapping("/checkOrder")
    public Integer checkOrder(@RequestBody order_ order){
        if(order.getOrder_id() == null)
        {
            return 500;
        }
        if(order.getState() == null)
        {
            return 500;
        }
        return orService.modifyOrderState(order.getOrder_id(), order.getState());
    }

    @PostMapping("/Get_refund")
    public Integer Get_refund(@RequestBody order_ order){
        if(order.getOrder_id() == null)
        {
            return 500;
        }
    
        return orService.calculateRefundPrice(order.getOrder_id());
    }
}
