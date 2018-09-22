package com.codeboyq.daybook;

public class ItemDto {

    private final int id;
    private final String content;

    public ItemDto(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
