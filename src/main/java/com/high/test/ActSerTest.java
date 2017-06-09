package com.high.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.high.entity.SearchActivityQueryModel;
import com.high.entity.SearchActivityResultModel;
import com.high.service.ActivityService;
import com.high.service.impl.ActivityServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class ActSerTest {

	@Autowired
	private ActivityService activityService;
//	private ActivityService activityService = new ActivityServiceImpl();
	
	@Test
	public void demo1(){
		SearchActivityQueryModel queryModel = new SearchActivityQueryModel();
		queryModel.setTopCategory("娱乐");
		queryModel.setSecCategory("三国杀");
		queryModel.setQuery("杀");
		Date date = new Date();
		queryModel.setStartTime(date);
		SearchActivityResultModel resultModel = activityService.searchActivity(queryModel);
		System.out.println(resultModel.getRecordCount());
		System.out.println(resultModel.getActivityList());
		System.out.println(resultModel);
	}
}
