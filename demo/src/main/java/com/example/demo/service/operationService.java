package com.example.demo.service;

import java.util.List;

import com.example.demo.Entity.operation_info;
import com.example.demo.Entity.user_role;
import com.example.demo.mapper.operation_infoMapper;
import com.example.demo.mapper.user_roleMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class operationService {
    
    @Autowired
    private operation_infoMapper oi_mapper;

    @Autowired
    private user_roleMapper ur_mapper;

    /*
     * 功能：修改管理人员信息
     * 返回：1：修改成功；0：修改失败；500：参数错误；400角色不存在
     */
    public int updateOperator(operation_info oi)
    {
        List<operation_info> role = oi_mapper.findById(oi.getUser_id());
        if(role.size() == 0)
        {
            return 400;
        }
        int i = oi_mapper.updateInfo(oi);
        return i;
    }

    /*
     * 功能：删除管理人员
     * 返回：1：删除成功；0：删除失败；500：参数错误；400角色不存在
     */
    public int deleteOperator(operation_info oi)
    {
        List<operation_info> operator = oi_mapper.findById(oi.getUser_id());
        if(operator.size() == 0)
        {
            return 400;
        }
        int i = oi_mapper.deleteById(oi.getUser_id());
        return i;
    }

    /*
     * 功能：添加管理人员,同时添加一个user_role对应表
     * 返回：1：添加成功；0：添加失败；500：参数错误
     */
    public int createOperator(operation_info oi, int role_id)
    {
        int an = oi_mapper.insert(oi);

        user_role ur = new user_role();
        ur.setUser_id(oi.getUser_id());
        ur.setRole_id(role_id);

        int an2 = ur_mapper.insert(ur);
        if(an2 != 1){
            return 0;
        }
        return an;
    }

    /*
     * 功能：按照页码，返回所有操作员的List，
     * 返回：user_info的list
     */
    public List<operation_info> getOperatorInfoByPage(int pageNum, int pageSize)
    {
        List<operation_info> oilist = oi_mapper.findPage(pageNum * pageSize, pageSize);
        
        return oilist;
    }

    /*
     * 功能：设置管理人员的角色
     * 返回：1设置成功，0设置失败，400未找到对应人员，500参数错误
     */
    public int setOperatorRole(user_role ur)
    {
        List<user_role> user_role_ = ur_mapper.findById(ur.getUser_id());
        if(user_role_.size() == 0)
        {
            return 400;
        }
        int i = ur_mapper.updateRoleId(ur);
        return i;
    }

    /*
     * 功能：删除User_role表中的值
     * 返回：1删除成功，0删除失败，500参数错误
     */
    public int deleteUR(int user_id)
    {
        return ur_mapper.deleteById(user_id);
    }
}
