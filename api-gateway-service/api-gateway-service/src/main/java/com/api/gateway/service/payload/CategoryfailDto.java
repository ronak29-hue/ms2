package com.api.gateway.service.payload;


public class CategoryfailDto {

    private String message;
    private boolean success;

    public CategoryfailDto() {
    }

    public CategoryfailDto(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
