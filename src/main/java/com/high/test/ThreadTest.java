package com.high.test;

import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by llw on 2017/7/18.
 */
public class ThreadTest {
    @Test
    public void demo1(){
        Runnable getToken = new Runnable() {
            @Override
            public void run() {
                System.out.println("in getToken runnable!!!");
            }
        };
        Runnable getToken2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("in getToken2 runnable!!!");
            }
        };
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(getToken, 1, 5, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(getToken2,0,10,TimeUnit.SECONDS);
        while(true) {

        }
    }
}
