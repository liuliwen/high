package com.high.service.impl;

import com.high.entity.Participate;
import com.high.entity.User;
import com.high.mapper.ParticipateMapper;
import com.high.service.ParticipateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/6/7.
 */
@Service("participateService")
public class ParticipateServiceImpl implements ParticipateService {

    @Autowired
    private ParticipateMapper participateMapper;
    @Override
    public List<User> getParticipatesByActivityId(String id) {
        return participateMapper.getParticipatesByActivityId(id);
    }

    @Override
    public Boolean deleteParticipateById(String activityId, String userId) {
        Participate participate = new Participate();
        participate.setActivityId(activityId);
        participate.setUserId(userId);
        int num = participateMapper.deleteParticipateById(participate);
        if(num>0)
            return  Boolean.TRUE;
        else
            return Boolean.FALSE;
    }
}
