package com.codeboyq.daybook.api.dto;

import java.util.Date;

public class ItemDto {

    private int id;
    private Date date; //TODO: Use other class?
    private String text;
    private String imageString;
    private int imageStatus;
    private boolean published;

    public ItemDto() {

    }

    public ItemDto(int id, Date date, String text, boolean published) {
        this.id = id;
        this.date = date;
        this.text = text;
        this.imageString = "";
        this.imageStatus = 0;
        this.published = published;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImageString() {
        return imageString;
    }

    public void setImageString(String imageString) {
        this.imageString = imageString;
    }

    public int getImageStatus() {
        return imageStatus;
    }

    public void setImageStatus(int imageStatus) {
        this.imageStatus = imageStatus;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }
}
