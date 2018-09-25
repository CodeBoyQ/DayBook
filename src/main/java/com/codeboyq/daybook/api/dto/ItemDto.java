package com.codeboyq.daybook.api.dto;

import java.time.LocalDate;

public class ItemDto {

    private int id;
    private LocalDate date;
    private String text;
    private String imagePath;
    private int imageStatus;
    private boolean published;

    public ItemDto() {

    }

    public ItemDto(int id, LocalDate date, String text, boolean published) {
        this.id = id;
        this.date = date;
        this.text = text;
        this.imagePath = "";
        this.imageStatus = 0;
        this.published = published;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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
