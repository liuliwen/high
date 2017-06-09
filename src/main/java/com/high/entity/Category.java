package com.high.entity;

public class Category {
    private String id;

    private String topCategory;

    private String secondaryCategory;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTopCategory() {
        return topCategory;
    }

    public void setTopCategory(String topCategory) {
        this.topCategory = topCategory == null ? null : topCategory.trim();
    }

    public String getSecondaryCategory() {
        return secondaryCategory;
    }

    public void setSecondaryCategory(String secondaryCategory) {
        this.secondaryCategory = secondaryCategory == null ? null : secondaryCategory.trim();
    }
}