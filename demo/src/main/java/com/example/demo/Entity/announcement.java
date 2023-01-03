package com.example.demo.Entity;

import lombok.Data;

@Data
public class announcement {
    private Integer id;
    private Integer user_id;
    private String title;
    private String content;
    private Integer type;
    private String sendtime;
}
