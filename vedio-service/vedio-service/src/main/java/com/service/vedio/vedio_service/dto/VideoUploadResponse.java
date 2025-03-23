package com.service.vedio.vedio_service.dto;

import lombok.Data;

@Data
public class VideoUploadResponse {

private String message;
private VideoDto videoDto;
private boolean success;
}
