package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Param;
import com.example.demo.Entity.order_;

@Mapper
public interface orderMapper {
    @Select("select * from order_ where order_id = #{order_id}") 
    List<order_> findByOrderId(@Param("order_id") Integer order_id);

    @Select("select * from order_ where user_id = #{user_id} order by order_create_time desc")
    List<order_> findByUserId(@Param("user_id") Integer user_id);

    @Select("select * from order_ order by order_create_time desc limit #{pageNum}, #{pageSize}")
    List<order_> findAllOrderByPage(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @Insert("insert into order_(rent_price, deposit_price, security_id, user_id, car_id, total_price, begintime, endtime, state)"+
    " values(#{rent_price}, #{deposit_price}, #{security_id}, #{user_id}, #{car_id}, #{total_price}, #{begintime}, #{endtime}, 0)")
    int insert(order_ order);

    @Delete("delete from order_ where order_id = #{order_id}")
    int deleteById(@Param("order_id") Integer order_id);

    @Update("update order_ set state = #{state} where order_id = #{order_id}")
    int updateState(@Param("order_id") Integer order_id, @Param("state") Integer state);

    @Update("update order_ set payInfo = #{payInfo} where order_id = #{order_id}")
    int updatePayInfo(@Param("order_id") Integer order_id, @Param("payInfo") String payInfo);
}
