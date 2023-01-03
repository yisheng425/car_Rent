package com.example.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.user_infoMapper;
import com.example.demo.Entity.user_info;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class user_infoService {
    
    @Autowired
    private user_infoMapper ui_mapper;

    /*
     * 功能：在用户表中创建一个新的用户，该功能由注册服务调用，保持一致性。
     * 返回：1：创建成功；0：创建失败
     */
    public int createNewUser(int user_id)
    {
        int i = ui_mapper.insert(user_id);

        return i;
    }

    /*
     * 功能：用户修改签名
     * 返回：1：修改成功；400：查询不到用户；401：其他修改失败。
     */
    public int editUserSign(int user_id, String sign)
    {
        List<user_info> user = ui_mapper.findById(user_id);
        if(user.size() == 0)
        {
            return 400;
        }
        int i = ui_mapper.updateSign(sign, user_id);
        if(i != 1){
            return 401;
        }
        return i;
    }

    /*
     * 功能：用户进行实名
     * 返回：1：修改成功；400：查询不到用户；401：其他修改失败。
     */
    public int identity(user_info ui)
    {
        ui.setIsIdentity(1);
        List<user_info> user = ui_mapper.findById(ui.getUser_id());
        if(user.size() == 0)
        {
            return 400;
        }
        int i = ui_mapper.update(ui);
        if(i != 1){
            return 401;
        }
        return i;
    }

    /*
     * 功能：查询用户是否实名
     * 返回：1：已经认证；0尚未认证；400：用户不存在；401：其他错误
     */
    public int getIsidentity(int user_id){
        List<user_info> user = ui_mapper.findById(user_id);
        if(user.size() == 0)
        {
            return 400;
        }
        if(user.size() > 1)
        {
            return 401;
        }
        if(user.get(0).getIsIdentity() == 1)
            return 1;

        return 0;
    }

    /*
     * 功能：按照页码，返回所有user的List，
     * 返回：user_info的list
     */
    public List<user_info> getUserInfoByPage(int pageNum, int pageSize)
    {
        List<user_info> ualist = ui_mapper.findPage(pageNum * pageSize, pageSize);
        
        return ualist;
    }

    /*
     * 功能：查询user_id
     * 返回：user_info的list
     */
    public List<user_info> getUserInfoByid(int user_id)
    {
        return ui_mapper.findById(user_id);
    }
}
