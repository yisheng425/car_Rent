package com.example.demo.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;
import com.example.demo.Entity.role_permission;

@Mapper
public interface role_permissionMapper {
    @Select("select * from role_permission where role_id = #{role_id}")
    List<role_permission> findById(@Param("role_id") Integer role_id);

    @Delete("delete from role_permission where role_id = #{role_id}")
    int deleteByRoleId(@Param("role_id") Integer id);

    @Insert("insert into role_permission(role_id, name) values(#{role_id}, #{name})")
    int insert(role_permission rp);
}
