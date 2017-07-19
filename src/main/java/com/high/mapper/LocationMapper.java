package com.high.mapper;

import com.high.entity.Location;

public interface LocationMapper {

	/**
	 * 添加 位置信息
	 * @param location
	 */
	void insertLocation(Location location);

	/**
	 * 通过地址id获得活动的id
	 * @param id
	 * @return
	 */
	Location findLocationById(String id);

	/**
	 * 通过位置id删除位置信息
	 * @param locationId
	 */
    void deleteLocationById(String locationId);

    int updateLocation(Location location);
}
