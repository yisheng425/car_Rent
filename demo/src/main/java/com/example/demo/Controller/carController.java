package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Controller.dto.pageDTO;
import com.example.demo.service.carService;
import com.example.demo.Entity.car;

@RequestMapping("/car")
@RestController
public class carController {
    @Autowired
    carService car_Service;

    @GetMapping("/GetUnrentedCar")
    public List<car> GetUnrentedCar(@RequestBody pageDTO page){
        return car_Service.getUnrentedCarInfoByPage(page.getPageNum(), page.getPageSize());
    }

    @GetMapping("/GetUnrentedCarNP")
    public List<car> GetUnrentedCarNP(){
        return car_Service.getUnrentedCarInfoByPage(0,50);
    }

    @GetMapping("/GetCarBypage")
    public List<car> GetCarBypage(){
        return car_Service.getAllCarInfoByPage(0, 50);
    }

    @PostMapping("/add_car")
    public int add_car(@RequestBody car c){
        if(c.getCar_rent_price() ==null || c.getCar_deposit_price() == null){
            return 500;
        }
        if(c.getCar_brand() == null || c.getCar_type() ==null){
            return 500;
        }
        if(c.getCar_number() == null || c.getCar_color() == null){
            return 500;
        }
        if(c.getPictureUrl() == null){
            return 500;
        }
        return car_Service.addCar(c);
    }

    @PostMapping("/Delete_car")
    public int Delete_car(@RequestBody car c){
        if(c.getCar_id() == null)
        {
            return 500;
        }
        return car_Service.deleteCar(c);
    }

    @PostMapping("/setCarSave")
    public int setCarSave(@RequestBody car c){
        if(c.getCar_id() == null)
        {
            return 500;
        }
        return car_Service.modifyState(c.getCar_id(), 3);
    }

    @PostMapping("/setCarReady")
    public int setCarReady(@RequestBody car c){
        if(c.getCar_id() == null)
        {
            return 500;
        }
        return car_Service.modifyState(c.getCar_id(), 0);
    }

    @PostMapping("/setCarBook")
    public int setCarBook(@RequestBody car c){
        if(c.getCar_id() == null)
        {
            return 500;
        }
        return car_Service.modifyState(c.getCar_id(), 1);
    }

    @PostMapping("/setCarRent")
    public int setCarRent(@RequestBody car c){
        if(c.getCar_id() == null)
        {
            return 500;
        }
        return car_Service.modifyState(c.getCar_id(), 2);
    }

    @PostMapping("/Modify")
    public int Modify(@RequestBody car c){
        if(c.getCar_id() == null)
        {
            return 500;
        }
        return car_Service.modifyInfo(c);
    }

    @PostMapping("/GetCarInfo")
    public List<car> GetCarInfo(@RequestBody car c){
        if(c.getCar_id() == null)
        {
            return null;
        }
        return car_Service.getCarInfoById(c.getCar_id());
    }
}
