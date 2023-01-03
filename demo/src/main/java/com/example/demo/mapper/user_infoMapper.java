package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Param;

import com.example.demo.Entity.user_info;

@Mapper
public interface user_infoMapper {

    @Select("select * from user_info")
    List<user_info> findAlluser();

    @Select("select * from user_info where user_id = #{user_id}")
    List<user_info> findById(@Param("user_id") Integer user_id);

    @Select("select * from user_info limit #{pageNum}, #{pageSize}")
    List<user_info> findPage(@Param("pageNum") Integer num, @Param("pageSize") Integer size);

    @Insert("insert into user_info(user_id, IsIdentity) values(#{user_id}, 0)")
    int insert(@Param("user_id") Integer id);

    int update(user_info ui);

    @Update("update user_info set sign = #{sign} where user_id = #{user_id}")
    int updateSign(@Param("sign") String sign, @Param("user_id") Integer user_id);

    int deleteById(@Param("id") Integer id);
}
