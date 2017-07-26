package com.high.mapper;

import com.high.entity.FormId;

import java.util.Date;

/**
 * Created by llw on 2017/7/19.
 */
public interface FormIdMapper {
    int insertFormId(FormId form);

    /**
     * 删除快要过期的formid
     * @param date 早于date的formiD将会过期，因此需要删除
     * @return
     */
    int deleteOutTimeFormId(String date);

    /**
     * 获取局离过期时间最近的formid，也就是最早创建的formid
     * @return
     */
    FormId getDelayingFormId();

    /**
     * 通过id从数据库中删除一条formid
     * @param formId
     */
    int deleteFormIdById(String formId);
}
