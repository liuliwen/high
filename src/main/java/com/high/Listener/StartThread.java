package com.high.Listener;

import com.high.utils.TimeUtils;
import com.high.utils.WeChatUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.concurrent.*;

/**
 * 当程序部署时会启动这个监听器，然后可以在这个监听器里面进行一些处理
 * 比如
 *      我们的项目中 需要定时获取token
 *      
 * Created by llw on 2017/7/18.
 */
public class StartThread implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Runnable getToken = new Runnable() {
            @Override
            public void run() {
                System.out.println("in getToken runnable!!!");
                WeChatUtils.getToken();
            }
        };
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        //获取token，每一个小时获取一次！网站部署的时候先获取一次！
        executorService.scheduleAtFixedRate(getToken,0,1, TimeUnit.HOURS);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

}
