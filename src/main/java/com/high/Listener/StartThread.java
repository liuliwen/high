package com.high.Listener;

import com.high.service.impl.FormIdServiceImpl;
import com.high.utils.TimeUtils;
import com.high.utils.WeChatUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.concurrent.*;

/**
 * 当程序部署时会启动这个监听器，然后可以在这个监听器里面进行一些处理
 * 比如
 *      我们的项目中 需要定时获取token
 *      定时删除将要过期的formId
 * Created by llw on 2017/7/18.
 */
public class StartThread implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //获取token
        Runnable getTokenRunnable = new Runnable() {
            @Override
            public void run() {
//                System.out.println("in getToken runnable!!!");
                WeChatUtils.getToken();
            }
        };
        //每隔半小时处理一下formid，将需要删除的一部分删掉
        Runnable deleteOutTimeFormIdRunnable = new Runnable() {
            @Override
            public void run() {
                FormIdServiceImpl formIdService = new FormIdServiceImpl();
                formIdService.deleteOutTimeFormId(0.5, 7);
            }
        };
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        //获取token，每一个小时获取一次！网站部署的时候先获取一次！
        executorService.scheduleAtFixedRate(getTokenRunnable,0,1, TimeUnit.HOURS);
        executorService.scheduleAtFixedRate(deleteOutTimeFormIdRunnable,0,30, TimeUnit.MINUTES);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

}
