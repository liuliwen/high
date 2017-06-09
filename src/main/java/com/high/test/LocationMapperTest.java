package com.high.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.high.entity.Location;
import com.high.service.LocationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/applicationContext.xml")
public class LocationMapperTest {

	@Autowired
	private LocationService locationService;
	@Test
	public void testInsert(){
		Location location = new Location();
		location.setLongitude(10.123);
		location.setLatitude(10.123);
		location.setLocationDescription("我电");
		locationService.insertLocation(location);
		System.out.println(location.getLocationId());
	}
}
