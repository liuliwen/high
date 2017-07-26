package com.high.test;

import com.high.entity.User;
import com.high.service.ParticipateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by llw on 2017/7/20.
 */
@ContextConfiguration("classpath:spring/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ParticipateServiceTest
{
    @Autowired
    public ParticipateService participateService;

    @Test
    public void demo1(){
        List<User> participates = participateService.getParticipatesByActivityId("171b7b9b-1506-4d27-bbf8-5e2b45c7fcb9");
        System.out.println(participates);
    }
}
