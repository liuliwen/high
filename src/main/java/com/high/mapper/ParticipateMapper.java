package com.high.mapper;

import com.high.entity.Participate;
import com.high.entity.User;

import java.util.List;

/**
 * Created by Administrator on 2017/6/7.
 */
public interface ParticipateMapper {

    List<User> getParticipatesByActivityId(String id);

    int deleteParticipateById(Participate participate);
}
