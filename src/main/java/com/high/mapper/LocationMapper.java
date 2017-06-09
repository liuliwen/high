package com.high.mapper;

import com.high.entity.Location;

public interface LocationMapper {

	void insertLocation(Location location);

	/**
	 * 通过地址id获得活动的id
	 * @param id
	 * @return
	 */
	Location findLocationById(String id);
}
