package com.high.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.high.entity.Activity;
import com.high.entity.Location;
import com.high.entity.SearchActivityResultModel;
import com.high.service.ActivityService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class ActivityServiceTest {

	@Autowired
	private ActivityService activityService;
	
	@Test
	public void demo1(){
		List<Activity> list = activityService.searchAllActivity();
		System.out.println(list);
	}
	
	@Test
	public void demo2(){
		Location location = new Location();
		location.setLatitude(34.233521);
		location.setLongitude(108.918785);
		
		SearchActivityResultModel model = activityService.defaultSearchActivity(location);
		List<Activity> list = model.getActivityList();
		System.out.println(list);
	}
}
