package com.example.roomservices;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class MyTask implements Runnable {
    @Override
    public void run() {
        try{
            log.info("begin sleep");
            TimeUnit.SECONDS.sleep(30);
            log.info("end sleep");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
