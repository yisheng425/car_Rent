package com.example.demo.Entity;

import lombok.Data;

@Data
public class car {
    private Integer car_id;
    private String car_type;
    private String car_brand;
    private Integer car_rent_price;
    private Integer car_deposit_price;
    private String car_color;
    private String car_number;
    private String pictureUrl;
    private Integer state;
}
