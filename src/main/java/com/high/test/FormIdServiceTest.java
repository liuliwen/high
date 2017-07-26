package com.high.test;

import com.high.entity.FormId;
import com.high.service.FormIdService;
import com.high.utils.TimeUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by llw on 2017/7/26.
 */
@ContextConfiguration("classpath:spring/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class FormIdServiceTest {

    @Autowired
    private FormIdService formIdService;

    @Test
    public void testDeleteOutTimeFormId(){
        boolean isSuccess = formIdService.deleteOutTimeFormId(0.5, 7);
    }

    @Test
    public void test2(){
        FormId form = formIdService.useFormId();
        System.out.println(form.getFormId()+" " + TimeUtils.formatDate(form.getCreateDate()));
    }
}
