package com.high.service;

import com.high.entity.Location;

public interface LocationService {

	void insertLocation(Location location);

	Location findLocationById(String id);
}
