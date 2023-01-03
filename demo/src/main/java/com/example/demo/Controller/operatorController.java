package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Controller.dto.operatorDTO;
import com.example.demo.Entity.operation_info;
import com.example.demo.Entity.user_account;
import com.example.demo.Entity.user_role;
import com.example.demo.service.operationService;
import com.example.demo.service.user_accountService;

@RequestMapping("/operator")
@RestController
public class operatorController {
    
    @Autowired
    operationService op_service;

    @Autowired
    user_accountService uaService;

    @PostMapping("/create")
    public Integer create(@RequestBody operatorDTO operator){
        if(operator.getUser_name() == null || operator.getPassword() == null || operator.getPhone_number() == null || operator.getName() == null || operator.getRole_id() == null)
        {
            return 500;
        }
        else{
            operation_info oi = new operation_info();
            user_account ua = new user_account();

            ua.setUser_name(operator.getUser_name());
            ua.setPhone_number(operator.getPhone_number());
            ua.setPassword(operator.getPassword());
            ua.setType(0);

            int an = uaService.insertUser2(ua);

            oi.setUser_id(uaService.getUserId(ua.getUser_name()));
            oi.setName(operator.getName());
            oi.setEmail(operator.getEmail());

           
            int an2 = op_service.createOperator(oi, operator.getRole_id());

            if(an == 1 && an2 == 1){
                return 1;
            }              
            else{
                return 0;
            }
        }
        
    }

    @PostMapping("/editInfo")
    public Integer editInfo(@RequestBody operation_info operator){
        if(operator.getUser_id() != null)
        {
            return op_service.updateOperator(operator);
        }
        else{
            return 500;
        }
    }

    @PostMapping("/setRole")
    public Integer setRole(@RequestBody user_role ur){
        if(ur.getUser_id() != null && ur.getRole_id() != null)
        {
            return op_service.setOperatorRole(ur);
        }
        else{
            return 500;
        }
    }

    @PostMapping("/delete")
    public Integer delete(@RequestBody operation_info operator){
        if(operator.getUser_id() != null)
        {
            if(op_service.deleteUR(operator.getUser_id()) == 1){
                if(op_service.deleteOperator(operator) == 1)
            {
                return uaService.deleteAccount(operator.getUser_id());
            }
            else{
                return 0;
            }
            }
            return 0;
        }
        else{
            return 500;
        }
    }

    @GetMapping("/getInfo")
    public List<operation_info> getInfo(){
        List<operation_info> data = op_service.getOperatorInfoByPage(0, 50);
        return data;
    }


}
