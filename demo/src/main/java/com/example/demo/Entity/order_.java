package com.example.demo.Entity;

import lombok.Data;

@Data
public class order_ {
    private Integer order_id;
    private Integer user_id;
    private Integer rent_price;
    private Integer deposit_price;
    private Integer security_id;
    private Integer car_id;
    private Integer total_price;

    private String begintime;
    private String endtime;
    //private String order_create_time;

    private Integer state;
    private String payInfo;
}
