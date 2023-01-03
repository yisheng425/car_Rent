package com.example.demo.service;

import java.util.List;

import com.example.demo.mapper.user_accountMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Controller.dto.userDTO;
import com.example.demo.Entity.user_account;

@Service
public class user_accountService {
    
    @Autowired
    private user_accountMapper uaMapper;

    @Autowired
    private user_infoService uiService;

    /*
     * 功能：用来查询用户账户表中，是否已经存在该手机号码
     * 返回：1已经有，提示找回密码；0没有，可以注册
     */
    public int ifHavePhone_number(String phone_number)
    {
        List<user_account> ualist = uaMapper.findUserByPhone_number(phone_number);
        if(ualist.size() == 0)
        {
            return 0;
        }
        return 1;
    }

    /*
     * 功能：用来查询用户账户表中，是否已经存在该用户名
     * 返回：1已经有，提示重新输入；0没有，可以注册
     */
    public int ifHaveUsername(String username)
    {
        List<user_account> ualist = uaMapper.findUserByUsername(username);
        if(ualist.size() == 0)
        {
            return 0;
        }
        return 1;
    }

    /*
     * 功能：用来通过用户名，获取id
     * 返回：id，-1则为找不到
     */
    public int getUserId(String username)
    {
        List<user_account> ualist = uaMapper.findUserByUsername(username);
        if(ualist.size() == 0)
        {
            return -1;
        }
        return ualist.get(0).getUser_id();
    }

    /*
     * 功能：用来向用户账户表中插入新用户，这里的用户type为1，客户类别。同时向user_info表中插入此用户
     * 返回：1插入成功；0插入失败
     */
    public int insertUser(user_account ua)
    {
        //确保手机和用户名不存在
        if(ifHavePhone_number(ua.getPhone_number()) == 1){
            return 0;
        }
        if(ifHaveUsername(ua.getUser_name()) == 1){
            return 0;
        }
        //插入
        int i = uaMapper.insert(ua);
        if(i == 1){
            List<user_account> ualist = uaMapper.findUserByUsername(ua.getUser_name());
            int user_id = ualist.get(0).getUser_id();
            int an = uiService.createNewUser(user_id);
            if(an == 1)
            {
                return 1;
            }
        }
        return 0;
    }

    /*
     * 功能：用来向用户账户表中插入新用户，这里的用户type为0，客户类别。同时不向user_info表中插入此用户
     * 返回：1插入成功；0插入失败
     */
    public int insertUser2(user_account ua)
    {
        //确保手机和用户名不存在
        if(ifHavePhone_number(ua.getPhone_number()) == 1){
            return 0;
        }
        if(ifHaveUsername(ua.getUser_name()) == 1){
            return 0;
        }
        //插入
        int i = uaMapper.insert(ua);
        return i;
    }

    /*
     * 功能：传入用户名和密码之后，确认是否正确，正确返回账户类型
     * 返回：0,代表管理账户；1代表用户账户；401代表用户名不存在；402代表密码不正确,403其他错误
     */
    public userDTO login(String username, String password)
    {
        userDTO userDto = new userDTO();
        List<user_account> ualist = uaMapper.findUserByUsername(username);
        if(ualist.size() == 0)
        {
            //此时用户名不存在
            userDto.setType(401);
            return userDto;
        }
        else if(ualist.size() != 1){
            userDto.setType(403);
            return userDto;
        }
        else{
            if(!ualist.get(0).getPassword().equals(password) )
            {
                userDto.setType(402);
                return userDto;
            }
        }
        int type_ = ualist.get(0).getType();
        userDto.setUser_id(ualist.get(0).getUser_id());
        userDto.setType(type_);
        userDto.setUser_name(username);
        return userDto;
    }

    /*
     * 功能：传入用户名和密码之后，确认是否正确，正确返回账户类型
     * 返回：0,代表管理账户；1代表用户账户；401代表用户名不存在；402代表密码不正确,403其他错误
     */
    public List<user_account> getAccount()
    {
        List<user_account> ualist = uaMapper.findAllUser();
        
        return ualist;
    }

    /*
     * 功能：删除用户
     * 返回：1删除成功，0删除失败，500参数错误
     */
    public int deleteAccount(int user_id)
    {
        return uaMapper.deleteById(user_id);
    }

    /*
     * 功能：修改密码
     * 返回：1修改成功，0修改失败，400找不到用户；500参数错误
     */
    public int editPass(user_account ua)
    {
        List<user_account> ualist = uaMapper.findUserByUsername(ua.getUser_name());
        if(ualist.size() == 0)
        {
            return 400;
        }
        if(!ualist.get(0).getPhone_number().equals(ua.getPhone_number())){
            return 0;
        }
        return uaMapper.updatePass(ua);
    }
}
