package com.high.service;

import com.high.entity.Location;

public interface LocationService {

	Location insertLocation(Location location);

	Location findLocationById(String id);

    void deleteLocationById(String locationId);

    boolean updateLocation(Location location);
}
