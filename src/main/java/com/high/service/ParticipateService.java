package com.high.service;

import com.high.entity.User;

import java.util.List;

/**
 * Created by Administrator on 2017/6/7.
 */
public interface ParticipateService {

    List<User> getParticipatesByActivityId(String id);

    Boolean deleteParticipateById(String activityId, String userId);
}
