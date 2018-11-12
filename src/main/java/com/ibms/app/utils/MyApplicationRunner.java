package com.ibms.app.utils;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @ClassNmae MyApplicationRunner
 * @Description TODO
 * @Author PWT
 * @Date 2018/8/31 13:20
 * @Version 1.0
 **/


@Component
public class MyApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        AdapterQueue.getInstance().createQueue();

    }
}
