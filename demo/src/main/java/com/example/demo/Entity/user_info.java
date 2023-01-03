package com.example.demo.Entity;

import lombok.Data;

@Data
public class user_info {
    private Integer user_id;
    private String identity_id;
    private String realName;
    private Integer IsIdentity;
    private String sign;
}
