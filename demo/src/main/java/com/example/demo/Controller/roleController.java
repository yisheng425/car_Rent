package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.roleService;
import com.example.demo.Entity.sys_role;
import com.example.demo.Entity.user_role;


@RequestMapping("/role")
@RestController
public class roleController {
    
    @Autowired
    roleService role_service;

    @GetMapping("/getAllRole")
    public List<sys_role> getAllRole(){
        return role_service.getRoleList();
    }

    @PostMapping("/createRole")
    public Integer createRole(@RequestBody sys_role role){
        if(role.getDescription() != null)
        {
            return role_service.createRole(role);
        }
        else{
            return 500;
        }
    }

    @PostMapping("/findRole")
    public Integer findRole(@RequestBody user_role ur){
        if(ur.getUser_id() != null)
        {
            return role_service.findRoleByUserId(ur.getUser_id());
        }
        else{
            return 500;
        }
    }

    @PostMapping("/changeState")
    public Integer changeState(@RequestBody sys_role role){
        if(role.getRole_id() == null)
        {
            return 500;           
        }
        return role_service.changeState(role);
    }

    @PostMapping("/deleteRole")
    public Integer deleteRole(@RequestBody sys_role role){
        if(role.getRole_id() == null)
        {
            return 500;           
        }
        return role_service.deleteRole(role.getRole_id());
    }

    @PostMapping("/editRole")
    public Integer editRole(@RequestBody sys_role role){
        if(role.getDescription() == null || role.getRole_id() == null)
        {
            return 500;           
        }
        else{
            return role_service.editRole(role);
        }
    }

}
