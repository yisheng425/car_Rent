package com.example.demo.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.example.demo.Entity.order_;
import com.example.demo.mapper.orderMapper;
import com.example.demo.Entity.security;
import com.example.demo.mapper.securityMapper;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class orderService {
    @Autowired
    private orderMapper order_mapper;

    @Autowired
    private securityMapper security_mapper;

    /*
     * 功能：查询所有保险
     * 返回：查询结果List
     */
    public List<security> GetAllSecurity(){
        return security_mapper.findAll();
    }

    /*
     * 功能：根据保险id查询保险
     * 返回：查询结果List
     */
    public List<security> GetSecurityById(int secutity_id){
        return security_mapper.findById(secutity_id);
    }

    /*
     * 功能：根据页码查询近期所有订单
     * 返回：查询结果List
     */
    public List<order_> GetAllOrder(int pageNum, int pageSize){
        return order_mapper.findAllOrderByPage(pageNum * pageSize, pageSize);
    }

    /*
     * 功能：查询用户的订单，时间排序
     * 返回：查询结果List
     */
    public List<order_> GetUserOrder(int user_id){
        return order_mapper.findByUserId(user_id);
    }

    /*
     * 功能：查询某一订单信息
     * 返回：查询结果List
     */
    public List<order_> GetOrderById(int order_id){
        return order_mapper.findByOrderId(order_id);
    }

    /*
     * 功能：创建订单，返回orderId
     * 返回：1创建成功；0创建失败；500参数错误
     */
    public int createOrder(order_ order){
        return order_mapper.insert(order);
    }

    /*
     * 功能：删除订单
     * 返回：1删除成功；0删除失败；500参数错误
     */
    public int deleteOrder(order_ order){
        return order_mapper.deleteById(order.getOrder_id());
    }

    /*
     * 功能：修改订单状态   0：未支付；1：已支付，待审核；2审核通过；3审核不通过；4取消订单；5：进行中；6订单结束，等候押金退还；7退还押金
     * 返回：1修改成功；0修改失败；500参数错误
     */
    public int modifyOrderState(int order_id, int state){
        return order_mapper.updateState(order_id, state);
    }

    private int StringtoIntNumber(String str){

        int num = 0;
        int size = str.length();
        for(int i=0; i<size; i++)
        {
            if(str.charAt(i) >='0' && str.charAt(i) <='9')
            {
                num = num * 10 + str.charAt(i) - '0';
            }
            else
            {
                return -1;
            }
        }

        //代表数字串
        return num;
    }

    /*
     * 功能：计算需要退款金额
     * 返回：1修改成功；0修改失败；500参数错误
     */
    public int calculateRefundPrice(int order_id){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date date = new Date(System.currentTimeMillis());
        String currentTime = formatter.format(date);
        String[] t1 = currentTime.split("-");
        List<order_> order = order_mapper.findByOrderId(order_id);
        String begintime = order.get(0).getBegintime();
        String[] t2 = begintime.split("-");

        int[] tt1 = new int[4];
        int[] tt2 = new int[4];

        for(int i=0;i<4;i++)
        {
            tt1[i] = StringtoIntNumber(t1[i]);
            tt2[i] = StringtoIntNumber(t2[i]);
        }
       

        //计算相差小时数
        int hour =0;
        if(tt1[1] > tt2[1]){
            return 0;
        } 
        if(tt1[1] == tt2[1]){
            if(tt2[2] >= tt1[2])
            {
                hour = (tt2[2] - tt1[2]) * 24 + (tt2[3] - tt1[3]);
            }
        }
        if(tt1[1] == 2)
        {
            hour = (tt2[2] - tt1[2] + 28) * 24 + (tt2[3] - tt1[3]);
        }
        else if(tt1[1] == 1 || tt1[1] == 3|| tt1[1] == 5|| tt1[1] == 7 || tt1[1] == 8 || tt1[1] == 10 || tt1[1] == 12)
        {
            hour = (tt2[2] - tt1[2] + 31) * 24 + (tt2[3] - tt1[3]);
        }
        else
        {
            hour = (tt2[2] - tt1[2] + 30) * 24 + (tt2[3] - tt1[3]);
        }

        if(hour>=24 && hour<48)
        {
            return (int)(order.get(0).getRent_price() * 0.9);
        }
        if(hour>=48){
            return (int)(order.get(0).getRent_price() * 0.95);
        }
        return 0;
    }
}
