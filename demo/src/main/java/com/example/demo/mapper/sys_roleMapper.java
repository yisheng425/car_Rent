package com.example.demo.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Param;
import com.example.demo.Entity.sys_role;

@Mapper
public interface sys_roleMapper {   
    
    @Insert("insert into sys_role(description, useable) values(#{description}, #{useable})")
    int insert(sys_role sr);

    @Select("select * from sys_role where role_id = #{role_id}")
    List<sys_role> findById(@Param("role_id") Integer role_id);

    @Select("select * from sys_role")
    List<sys_role> findAllRole();

    @Update("update sys_role set description = #{description} where role_id = #{role_id}")
    int updateDes(sys_role sr);

    @Update("update sys_role set useable = #{useable} where role_id = #{role_id}")
    int updateState(sys_role sr);

    @Delete("delete from sys_role where role_id = #{role_id}")
    int deleteById(@Param("role_id") Integer id);
}
