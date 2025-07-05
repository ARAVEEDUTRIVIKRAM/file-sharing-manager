package com.company.fileSharingManagement.model;

import java.time.LocalDateTime;

public class FileModel {

    private Long id; // ✅ Add this line

    private String fileName;
    private String uploadedBy;
    private LocalDateTime expiryTime;
    private byte[] fileData;

    // ✅ Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(LocalDateTime expiryTime) {
        this.expiryTime = expiryTime;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }
    
    public String getTimeLeft() {
        if (expiryTime == null) return "Unknown";

        java.time.Duration duration = java.time.Duration.between(LocalDateTime.now(), expiryTime);
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;

        if (duration.isNegative()) {
            return "Expired";
        }

        return hours + " hours " + minutes + " minutes left";
    }
    
    public String getReadableFileSize() {
        if (fileData == null) return "0 KB";
        int sizeInBytes = fileData.length;
        if (sizeInBytes < 1024) {
            return sizeInBytes + " B";
        } else if (sizeInBytes < 1024 * 1024) {
            return String.format("%.2f KB", sizeInBytes / 1024.0);
        } else {
            return String.format("%.2f MB", sizeInBytes / (1024.0 * 1024.0));
        }
    }


}


