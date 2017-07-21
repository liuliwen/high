package com.high.controller;

import com.high.entity.FormId;
import com.high.service.FormIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by llw on 2017/7/19.
 */
@RequestMapping("formId")
@Controller
public class FormIdController {
    @Autowired
    private FormIdService formIdService;

    @RequestMapping("/saveFormId.do")
    public @ResponseBody Map<String,Object> saveFormId(String formId){
        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println(formId);
        if(formId!=null && !"undefined".equals(formId)){
            System.out.println(formId);
            FormId form = new FormId();
            form.setFormId(formId);
            form.setCreateDate(new Date());
            if(formIdService.insertFormId(form)){
                map.put("code",200);
                return map;
            }
        }
        map.put("code",500);
        return map;
    }
}
