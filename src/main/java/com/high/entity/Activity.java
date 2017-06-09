package com.high.entity;

import java.util.Date;
import java.util.List;


public class Activity {
    private String activityId;

    private String categotyId;

    private String content;

    private String comment;

    private Date startTime;

    private Date endTime;

    private String activityLocationId;

    private Integer minNum;

    private Integer maxNum;

    private String creatorId;

    private Boolean isPublic;

    private String creatorLocationId;

    private Double distance;
    
    private Location activityLocation;
    private User creator;
    private List<User> participate;
    private Category category;

    public List<User> getParticipate() {
        return participate;
    }

    public void setParticipate(List<User> participate) {
        this.participate = participate;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public Location getActivityLocation() {
		return activityLocation;
	}

	public void setActivityLocation(Location activityLocation) {
		this.activityLocation = activityLocation;
	}

    public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId == null ? null : activityId.trim();
    }

    public String getCategotyId() {
        return categotyId;
    }

    public void setCategotyId(String categotyId) {
        this.categotyId = categotyId == null ? null : categotyId.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
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

    public String getActivityLocationId() {
        return activityLocationId;
    }

    public void setActivityLocationId(String activityLocationId) {
        this.activityLocationId = activityLocationId == null ? null : activityLocationId.trim();
    }

    public Integer getMinNum() {
        return minNum;
    }

    public void setMinNum(Integer minNum) {
        this.minNum = minNum;
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
        this.creatorId = creatorId == null ? null : creatorId.trim();
    }

    public Boolean getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}

	public String getCreatorLocationId() {
        return creatorLocationId;
    }

    public void setCreatorLocationId(String creatorLocationId) {
        this.creatorLocationId = creatorLocationId == null ? null : creatorLocationId.trim();
    }

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

}