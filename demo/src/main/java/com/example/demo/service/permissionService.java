package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.Entity.sys_permission;
import com.example.demo.mapper.sys_permissionMapper;
import com.example.demo.Entity.role_permission;
import com.example.demo.mapper.role_permissionMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class permissionService {

    @Autowired
    private sys_permissionMapper sp_mapper;

    @Autowired
    private role_permissionMapper rp_mapper;

    @Autowired
    private roleService rService;

    /*
     * 功能：返回所有的权限
     * 返回：列表
     */
    public List<sys_permission> getAllPermission(){
        return sp_mapper.findAll();
    }

    /*
     * 功能：删除某角色所有权限
     * 返回：>1删除成功删除条数，0删除失败，400找不到，500参数错误
     */
    public int deletePermissionByRoleId(int role_id){
        List<role_permission> data = rp_mapper.findById(role_id);
        if(data.size() > 0){
            return rp_mapper.deleteByRoleId(role_id);
        }
        else{
            return 400;
        }
    }

    /*
     * 功能：添加权限
     * 返回：1添加成功，0添加失败，400找不到角色，500参数错误
     */
    public int addPermissionByRoleId(role_permission rp){
        return rp_mapper.insert(rp);
    }

    /*
     * 功能：返回某角色的所有权限
     * 返回：只含有权限名称的列表
     */
    public List<String> findPermissionByRoleId(int role_id){
        List<role_permission> data = rp_mapper.findById(role_id);
        List<String> answer = new ArrayList<String>();
        for(int i =0;i<data.size();i++)
        {
            answer.add(data.get(i).getName());
        }
        return answer;
    }

    /*
     * 功能：返回某管理员的所有权限
     * 返回：只含有权限名称的列表
     */
    public List<String> findPermissionByUserId(int user_id){
        
        int role_id = rService.findRoleByUserId(user_id);

        List<role_permission> data = rp_mapper.findById(role_id);
        List<String> answer = new ArrayList<String>();
        for(int i =0;i<data.size();i++)
        {
            answer.add(data.get(i).getName());
        }
        return answer;
    }
    
}
