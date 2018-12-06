package com.ibms.app.utils;

import com.ibms.app.service.ValueChanger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @ClassNmae task
 * @Description TODO
 * @Author PWT
 * @Date 2018/11/6 11:01
 * @Version 1.0
 **/

@Component
public class Task {


    @Autowired
    private ValueChanger valueChanger;


    //每隔2秒执行1次
    @Scheduled(fixedRate  = 2000 )
    public void getPower(){

        System.out.println("任务开始");

        valueChanger.ChangeAirConditioner();
        valueChanger.ChangeOther();
        valueChanger.ChangeTemperature();
    }

}
