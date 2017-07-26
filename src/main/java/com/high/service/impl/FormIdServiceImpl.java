package com.high.service.impl;

import com.high.entity.FormId;
import com.high.mapper.FormIdMapper;
import com.high.service.FormIdService;
import com.high.utils.TimeUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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

    @Override
    public boolean deleteFormId(String formId) {
        int rows = formIdMapper.deleteFormIdById(formId);;
        if(rows ==1){
            return  true;
        }
        return false;
    }

    @Override
    public FormId useFormId() {
        FormId form = formIdMapper.getDelayingFormId();
        formIdMapper.deleteFormIdById(form.getFormId());
        return form;
    }

    /**
     * 删除即将过期的formId
     * @param hours 间隔hours小时删除一次，因此在hours时间内会过期的formId也要被删除
     * @param days  formId多长时间会过期
     * @return
     */
    @Override
    public boolean deleteOutTimeFormId(double hours,double days) {
        Date nowDate = new Date();
        double delayDate = days * 24;       //设置为double类型的天数，则为了容易处理则换成hours了
        double periodMinute = hours * 60;   //设置间隔为double类型，则换成mintues
        Date date = DateUtils.addMinutes(nowDate, (int) periodMinute);
        date = DateUtils.addHours(date, -1 * (int) delayDate);
        int rows = formIdMapper.deleteOutTimeFormId(TimeUtils.formatDate(date));
        return true;
    }
}
