package com.high.service;

import java.util.List;

import com.high.entity.Activity;
import com.high.entity.Location;
import com.high.entity.SearchActivityQueryModel;
import com.high.entity.SearchActivityResultModel;

public interface ActivityService {

	/**
	 * 创建活动
	 * @param activity
	 */
	Activity createActivity(Activity activity);

	/**
	 * 搜索活动
	 * @param queryModel
	 * @return
	 */
	SearchActivityResultModel searchActivity(SearchActivityQueryModel queryModel);

	/**
	 * 获得所有的活动
	 * @return
	 */
	List<Activity> searchAllActivity();
	
	/**
	 * 获得当前时间之后的活动
	 * @return
	 */
	List<Activity> searchActivityFromNow();
	
	/**
	 * 默认的搜索活动，即用户点击寻找活动之后，还未进行搜索后者筛选操作
	 * @return
	 */
	SearchActivityResultModel defaultSearchActivity(Location location);


	Activity findActivityById(String id);

	void deleteActivityById(String id);
}
