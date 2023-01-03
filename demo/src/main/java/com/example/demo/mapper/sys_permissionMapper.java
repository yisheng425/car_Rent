package com.example.demo.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.example.demo.Entity.sys_permission;

@Mapper
public interface sys_permissionMapper {
    @Select("select * from sys_permission")
    List<sys_permission> findAll();
}
