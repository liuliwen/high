package com.high.service.impl;

import com.high.entity.FormId;
import com.high.mapper.FormIdMapper;
import com.high.service.FormIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by llw on 2017/7/19.
 */
@Service("formIdService")
public class FormIdServiceImpl implements FormIdService{

    @Autowired
    private FormIdMapper formIdMapper;
    @Override
    public boolean insertFormId(FormId form) {
        int row=formIdMapper.insertFormId(form);
        if(row==1)
            return true;
        return false;
    }
}
