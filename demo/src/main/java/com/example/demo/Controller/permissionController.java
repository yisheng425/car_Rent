package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.operation_info;
import com.example.demo.Entity.role_permission;
import com.example.demo.Entity.sys_permission;
import com.example.demo.Entity.sys_role;
import com.example.demo.service.permissionService;

@RequestMapping("/permission")
@RestController
public class permissionController {

    @Autowired
    permissionService pService;

    @GetMapping("/getAll")
    public List<sys_permission> getAll(){
        return pService.getAllPermission();
    }

    @GetMapping("/findByRoleId")
    public List<String> findByRoleId(@RequestBody sys_role role){
        return pService.findPermissionByRoleId(role.getRole_id());
    }

    @GetMapping("/findByUserId")
    public List<String> findByUserId(@RequestBody operation_info oi){
        return pService.findPermissionByUserId(oi.getUser_id());
    }

    @PostMapping("/deleteAllPermission")
    public Integer deleteAllPermission(@RequestBody sys_role role){
        return pService.deletePermissionByRoleId(role.getRole_id());
    }

    //批量添加，有待商榷
    @PostMapping("/addPermission")
    public Integer addPermission(@RequestBody role_permission rp){
        return pService.addPermissionByRoleId(rp);
    }
    
}
