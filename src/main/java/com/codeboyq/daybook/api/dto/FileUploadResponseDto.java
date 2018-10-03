package com.codeboyq.daybook.api.dto;

public class FileUploadResponseDto {
    private int itemId;
    private String fileDownloadUri;
    private String fileType;
    private long size;

    public FileUploadResponseDto(int itemId, String fileDownloadUri, String fileType, long size) {
        this.itemId = itemId;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
    }

    public int getItemId() { return itemId; }

    public void setItemId(int itemId) { this.itemId = itemId; }

    public String getFileDownloadUri() {
        return fileDownloadUri;
    }

    public void setFileDownloadUri(String fileDownloadUri) {
        this.fileDownloadUri = fileDownloadUri;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}