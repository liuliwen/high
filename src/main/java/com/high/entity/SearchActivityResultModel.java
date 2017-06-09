package com.high.entity;

import java.util.List;

public class SearchActivityResultModel {
	// 活动列表
	private List<Activity> activityList;
	// 活动总数
	private Long recordCount;
	// 总页数
	private int pageCount;
	// 当前页
	private int curPage;

	public Long getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(Long recordCount) {
		this.recordCount = recordCount;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public List<Activity> getActivityList() {
		return activityList;
	}

	public void setActivityList(List<Activity> activityList) {
		this.activityList = activityList;
	}

}
