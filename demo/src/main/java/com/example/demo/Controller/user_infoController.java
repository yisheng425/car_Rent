package com.example.demo.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.Entity.user_info;
import com.example.demo.service.user_infoService;

@RequestMapping("/user")
@RestController
public class user_infoController {

    @Autowired
    user_infoService ui_service;

    @GetMapping("/getInfo")
	public List<user_info> findpage()
	{
        List<user_info> data = ui_service.getUserInfoByPage(0, 50);
        return data;
    }

    @PostMapping("/editUserSign")
    public Integer editSign(@RequestBody user_info ui)
    {
        if(ui.getUser_id() == null || ui.getSign() == null)
            return 500;
        else{
            return ui_service.editUserSign(ui.getUser_id(), ui.getSign());
        }     
    }

    @PostMapping("/identity")
    public Integer identity(@RequestBody user_info ui)
    {
        if(ui.getUser_id() == null || ui.getIdentity_id() == null || ui.getRealName() == null)
            return 500;
        else{
            return ui_service.identity(ui);
        }     
    }

    @PostMapping("/IsIdentity")
    public Integer IsIdentity(@RequestBody user_info ui)
    {
        if(ui.getUser_id() == null)
            return 500;
        else{
            return ui_service.getIsidentity(ui.getUser_id());
        }     
    }
}