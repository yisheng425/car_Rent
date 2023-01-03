package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.order_;
import com.example.demo.mapper.orderMapper;

@Service
public class payService {

    @Autowired
    private orderMapper order_mapper;
    /*
     * 功能：表示支付，存储支付信息。
     * 返回：1代表成功，0代表失败。
     */
    public int savePayInfo(order_ order)
    {
        return order_mapper.updatePayInfo(order.getOrder_id(), order.getPayInfo());
    }

    /*
     * 功能：表示预约后，退款
     * 返回：0表示失败，数值表示退款成功
     */
    public int refund(order_ order)
    {
        //计算退款
        //退款
        return order_mapper.updateState(order.getOrder_id(), 4);
    }

    /*
     * 功能：表示审核不通过后，全额退款
     * 返回：0表示失败，1表示退款成功
     */
    public int refundAlLMoney(order_ order)
    {
        //计算退款
        System.out.println("全额退款");
        //退款
        return 1;
    }

    /*
     * 功能：完成订单后，退押金
     * 返回：1表示成功，0表示失败
     */
    public int refundDeposit(order_ order)
    {
        return order_mapper.updateState(order.getOrder_id(), 7);
    }
}
