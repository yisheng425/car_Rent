package com.example.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.sys_roleMapper;
import com.example.demo.mapper.user_roleMapper;
import com.example.demo.Entity.user_role;
import com.example.demo.Entity.sys_role;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class roleService {
    
    @Autowired
    private user_roleMapper ur_mapper;

    @Autowired
    private sys_roleMapper sr_mapper;

    /*
     * 功能：创建一个空权限的角色，并添加描述，开始处于不启用状态
     * 返回：1：添加成功；0：添加失败；500：参数错误
     */
    public int createRole(sys_role sr)
    {
        sr.setUseable(0);
        int i = sr_mapper.insert(sr);

        return i;
    }

    /*
     * 功能：修改角色的描述
     * 返回：1：修改成功；0：修改失败；500：参数错误；400角色不存在
     */
    public int editRole(sys_role sr)
    {
        List<sys_role> role = sr_mapper.findById(sr.getRole_id());
        if(role.size() == 0)
        {
            return 400;
        }
        int i = sr_mapper.updateDes(sr);
        return i;
    }

    /*
     * 功能：删除一角色
     * 返回：1：删除成功；0：删除失败；500：参数错误；400角色不存在
     */
    public int deleteRole(int role_id)
    {
        List<sys_role> role = sr_mapper.findById(role_id);
        if(role.size() == 0)
        {
            return 400;
        }
        int i = sr_mapper.deleteById(role_id);
        return i;
    }

    /*
     * 功能：查询管理员对应的角色id
     * 返回：角色id；400：管理员不存在
     */
    public int findRoleByUserId(int user_id)
    {
        List<user_role> user_role = ur_mapper.findById(user_id);
        if(user_role.size() == 0)
        {
            return 400;
        }
        return user_role.get(0).getRole_id();
    }

    /*
     * 功能：修改角色的启用状态
     * 返回：1：修改成功；0：修改失败；500：参数错误；400角色不存在
     */
    public int changeState(sys_role sr)
    {
        List<sys_role> role = sr_mapper.findById(sr.getRole_id());
        if(role.size() == 0)
        {
            return 400;
        }
        if(role.get(0).getUseable() == 1){
            sr.setUseable(0);
        }
        else{
            sr.setUseable(1);
        }
        int i = sr_mapper.updateState(sr);
        return i;
    }

    /*
     * 功能：获取现在所有角色列表
     * 返回：角色列表
     */
    public List<sys_role> getRoleList(){
        return sr_mapper.findAllRole();
    }
}
