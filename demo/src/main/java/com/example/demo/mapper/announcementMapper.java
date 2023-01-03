package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Param;

import com.example.demo.Entity.announcement;

@Mapper
public interface announcementMapper {
    @Select("select * from announcement limit #{pageNum}, #{pageSize}")
    List<announcement> findAllAnnouncement(@Param("pageNum") Integer num, @Param("pageSize") Integer size);

    @Select("select * from announcement where type = 1 limit #{pageNum}, #{pageSize}")
    List<announcement> getSentAnnouncement(@Param("pageNum") Integer num, @Param("pageSize") Integer size);

    @Select("select * from announcement where type = 0 and user_id = #{user_id}")
    List<announcement> getUnSentAnnouncement(@Param("user_id") Integer user_id);

    @Select("select * from announcement where user_id = #{user_id} limit #{pageNum}, #{pageSize}")
    List<announcement> getMySentAnnouncement(@Param("pageNum") Integer num, @Param("pageSize") Integer size, @Param("user_id") Integer user_id);

    @Update("update announcement set title = #{title}, content = #{content} where id = #{id}")
    int updateInfo(announcement an);

    //发布为1，不发布为0
    @Update("update announcement set type = #{type} where id = #{id}")
    int updateState(@Param("id") Integer id, @Param("type") Integer type);

    @Delete("delete from announcement where id = #{id}")
    int deleteById(@Param("id") Integer id);

    @Insert("insert into announcement(title, content, user_id, type) values(#{title}, #{content}, #{user_id}, 0)")
    int insert(announcement an);
}
