package com.example.demo.service;

import java.util.List;
import com.example.demo.Entity.car;
import com.example.demo.mapper.carMapper;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class carService {

    @Autowired
    private carMapper car_mapper;

    /*
     * 功能：分页查询所有汽车信息
     * 返回：查询结果List
     */
    public List<car> getAllCarInfoByPage(int pageNum, int pageSize){
        return car_mapper.findAllCar(pageNum * pageSize, pageSize);
    }

    /*
     * 功能：某一条车辆信息
     * 返回：查询结果List
     */
    public List<car> getCarInfoById(int order_id){
        return car_mapper.findCarById(order_id);
    }

    /*
     * 功能：分页查询所有未出租汽车信息     0未出租，1预约中，2出租中，3保养中
     * 返回：查询结果List
     */
    public List<car> getUnrentedCarInfoByPage(int pageNum, int pageSize){
        return car_mapper.getUnrentedCar(pageNum * pageSize, pageSize);
    }

    /* 
     * 功能：添加新的车辆时
     * 返回：1添加成功，0添加失败，500参数错误
     */
    public int addCar(car car_){
        return car_mapper.insert(car_);
    }

    /*
     * 功能：删除车辆
     * 返回：1删除成功，0删除失败，500参数错误
     */
    public int deleteCar(car car_){
        return car_mapper.deleteById(car_.getCar_id());
    }

    /*
     * 功能：修改车辆信息
     * 返回：1修改成功，0修改失败，500参数错误，400：未找到对应车辆
     */
    public int modifyInfo(car car_){
        List<car> cars = car_mapper.findCarById(car_.getCar_id());
        if(cars.size() == 0)
        {
            return 400;
        }
        return car_mapper.updateCarInfo(car_);
    }

    /*
     * 功能：修改车辆状态
     * 返回：1修改成功，0修改失败，500参数错误，400：未找到对应车辆
     */
    public int modifyState(int car_id, int state){
        List<car> cars = car_mapper.findCarById(car_id);
        if(cars.size() == 0)
        {
            return 400;
        }
        return car_mapper.updateState(car_id, state);
    }
}
