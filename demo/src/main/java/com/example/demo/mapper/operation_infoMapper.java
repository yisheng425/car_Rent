package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Param;
import com.example.demo.Entity.operation_info;

@Mapper
public interface operation_infoMapper {

    @Insert("insert into operation_info(user_id, name, email) values(#{user_id}, #{name}, #{email})")
    int insert(operation_info oi);
    
    @Select("select * from operation_info where user_id = #{user_id}")
    List<operation_info> findById(@Param("user_id") Integer user_id);

    @Select("select * from operation_info where name = #{name}")
    List<operation_info> findByName(@Param("name") String name);

    @Select("select * from operation_info")
    List<operation_info> findAll();

    @Update("update operation_info set name = #{name}, email = #{email} where user_id = #{user_id}")
    int updateInfo(operation_info oi);

    @Delete("delete from operation_info where user_id = #{user_id}")
    int deleteById(@Param("user_id") Integer id);

    @Select("select * from operation_info limit #{pageNum}, #{pageSize}")
    List<operation_info> findPage(@Param("pageNum") Integer num, @Param("pageSize") Integer size);

    
}
