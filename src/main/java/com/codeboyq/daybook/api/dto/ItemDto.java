package com.codeboyq.daybook.api.dto;

import java.util.Date;

public class ItemDto {

    private final int id;
    private final Date date;
    private final String content;

    public ItemDto(int id, Date date, String content) {
        this.id = id;
        this.date = date;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
