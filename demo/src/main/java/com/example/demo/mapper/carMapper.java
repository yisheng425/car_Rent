package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Param;
import com.example.demo.Entity.car;

@Mapper
public interface carMapper {
    @Select("select * from car where car_id = #{car_id}")
    List<car> findCarById(@Param("car_id") Integer car_id);

    @Select("select * from car limit #{pageNum}, #{pageSize}")
    List<car> findAllCar(@Param("pageNum") Integer num, @Param("pageSize") Integer size);

    @Select("select * from car where state = 0 limit #{pageNum}, #{pageSize}")
    List<car> getUnrentedCar(@Param("pageNum") Integer num, @Param("pageSize") Integer size);

    @Insert("insert into car(car_type, car_brand, car_rent_price, car_deposit_price, car_color, car_number, pictureUrl, state)" + 
    " values(#{car_type}, #{car_brand}, #{car_rent_price}, #{car_deposit_price}, #{car_color}, #{car_number}, #{pictureUrl}, 0)")
    int insert(car car_);
    
    @Update("update car set state = #{state} where car_id = #{car_id}")
    int updateState(@Param("car_id") Integer car_id, @Param("state") Integer state);

    @Update("update car set car_type = #{car_type}, car_brand = #{car_brand}, car_rent_price = #{car_rent_price}, car_deposit_price = #{car_deposit_price}, car_color = #{car_color}, car_number = #{car_number}, pictureUrl = #{pictureUrl} where car_id = #{car_id}")
    int updateCarInfo(car car_);

    @Delete("delete from car where car_id = #{car_id}")
    int deleteById(@Param("car_id") Integer car_id);

    // 
    //
}
