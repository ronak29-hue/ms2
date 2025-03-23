package com.service.vedio.vedio_service.dto;

import lombok.*;


@Data
public class VideoDto {

    private String id;
    private String title;
    private String desc;
    private String filePath;
    private String contentType;
    private String courseId;

private CourseDto courseDto;

}
