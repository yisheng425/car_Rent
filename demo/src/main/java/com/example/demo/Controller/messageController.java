package com.example.demo.Controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.Entity.user_account;
import com.example.demo.service.messageService;
import com.example.demo.service.user_accountService;

@RequestMapping("/msg")
@RestController
public class messageController {

    @Autowired
    private messageService messageservice;

    @PostMapping("/sendVericode")
    public Integer sendVericode(@RequestBody user_account ua) throws Exception
    {
        if(ua.getPhone_number() != null){
            int state = messageservice.SendVericode(ua.getPhone_number());
            return state;
        }
        return 500;
    }


}