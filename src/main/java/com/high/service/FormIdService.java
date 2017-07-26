package com.high.service;

import com.high.entity.FormId;

/**
 * Created by llw on 2017/7/19.
 */
public interface FormIdService {
    boolean insertFormId(FormId form);

    boolean deleteFormId(String formId);

    /**
     * 使用一个formId，从数据库查询一个比较早的formID（离过期时间最近的），并取出，然后从数据库删除
     * @return
     */
    FormId useFormId();

    /**
     * 删除即将过期的formid
     * @param hours
     * @param days
     * @return
     */
    boolean deleteOutTimeFormId(double hours,double days);
}
