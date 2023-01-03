package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.demo.Entity.security;

@Mapper
public interface securityMapper {
    @Select("select * from security where security_id = #{security_id}")
    List<security> findById(@Param("security_id") Integer security_id);

    @Select("select * from security")
    List<security> findAll();
}
