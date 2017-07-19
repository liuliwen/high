package com.high.mapper;

import java.util.List;

import com.high.entity.Activity;

public interface ActivityMapper {

	/**
	 * 向数据库中插入活动
	 * @param activity
	 */
	int insertActivity(Activity activity);

	/**
	 * 查找所有的活动
	 * @return
	 */
	List<Activity> searchAllActivity();

	/**
	 * 通过活动id查找活动信息
	 * @param id
	 * @return
	 */
	Activity findActivityById(String id);


	void deleteActivityById(String id);
}
