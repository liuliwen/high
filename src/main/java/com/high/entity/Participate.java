package com.high.entity;

public class Participate {
    private String parId;

    private String activityId;

    private String userId;

    public String getParId() {
        return parId;
    }

    public void setParId(String parId) {
        this.parId = parId == null ? null : parId.trim();
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId == null ? null : activityId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }
}