package com.high.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.high.entity.Location;
import com.high.mapper.LocationMapper;
import com.high.service.LocationService;

@Service("locationService")
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationMapper locationMapper;
	@Override
	public Location insertLocation(Location location) {
		location.setLocationId(UUID.randomUUID().toString());
		locationMapper.insertLocation(location);
		return location;
	}

	@Override
	public Location findLocationById(String id) {
		return locationMapper.findLocationById(id);
	}

	@Override
	public void deleteLocationById(String locationId) {
		locationMapper.deleteLocationById(locationId);
	}

	@Override
	public boolean updateLocation(Location location) {
		int row = locationMapper.updateLocation(location);
		if(row==1){
			return true;
		}
		return false;
	}

}
