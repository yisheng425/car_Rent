package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.order_;
import com.example.demo.service.payService;

@RequestMapping("/pay")
@RestController
public class payController {
    @Autowired
    payService pService;

    @PostMapping("/payment")
    public Integer cancel(@RequestBody order_ order){
        if(order.getOrder_id() == null || order.getPayInfo() == null)
        {
            return 500;
        }
        return pService.savePayInfo(order);
    }

    @PostMapping("/refund")
    public Integer refund(@RequestBody order_ order){
        if(order.getOrder_id() == null)
        {
            return 500;
        }
        return pService.refund(order);
    }

    @PostMapping("/refundAll")
    public Integer refundAll(@RequestBody order_ order){
        if(order.getOrder_id() == null)
        {
            return 500;
        }
        return pService.refundAlLMoney(order);
    }

    @PostMapping("/refundDeposit")
    public Integer refundDeposit(@RequestBody order_ order){
        if(order.getOrder_id() == null)
        {
            return 500;
        }
        return pService.refundDeposit(order);
    }
}
