package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Param;
import com.example.demo.Entity.user_role;

@Mapper
public interface user_roleMapper {
    @Select("select * from user_role where user_id = #{user_id}")
    List<user_role> findById(@Param("user_id") Integer id);

    @Update("update user_role set role_id = #{role_id} where user_id = #{user_id}")
    int updateRoleId(user_role ur);

    @Insert("insert into user_role(user_id, role_id) values(#{user_id}, #{role_id})")
    int insert(user_role ur);

    @Delete("delete from user_role where user_id = #{user_id}")
    int deleteById(@Param("user_id") Integer id);
}
