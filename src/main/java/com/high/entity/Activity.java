package com.high.entity;

import java.util.Date;
import java.util.List;


public class Activity {
    private String activityId;

    private String categoryId;

    private String content;

    private String comment;

    private Date startTime;

    private Date endTime;

    private String locationId;

    private Integer maxNum;

    private String creatorId;

    private Boolean isPublic;

    private Date deadline;

    private Double distance;
    
    private Location activityLocation;
    private User creator;
    private List<User> participate;
    private Category category;
    private String limitLocationId;
    private Location limitLocation;

    public String getLimitLocationId() {
        return limitLocationId;
    }

    public void setLimitLocationId(String limitLocationId) {
        this.limitLocationId = limitLocationId;
    }

    public Location getLimitLocation() {
        return limitLocation;
    }

    public void setLimitLocation(Location limitLocation) {
        this.limitLocation = limitLocation;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getActivityId() {
        return activityId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public Integer getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Location getActivityLocation() {
        return activityLocation;
    }

    public void setActivityLocation(Location activityLocation) {
        this.activityLocation = activityLocation;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public List<User> getParticipate() {
        return participate;
    }

    public void setParticipate(List<User> participate) {
        this.participate = participate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "content='" + content + '\'' +
                ", comment='" + comment + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", maxNum=" + maxNum +
                ", activityLocation=" + activityLocation +
                ", creator=" + creator +
                ", category=" + category +
                '}';
    }
}