package com.service.vedio.vedio_service.dto;

import lombok.Data;
import org.springframework.core.io.Resource;

@Data
public class ResourceContentType {

    private Resource resource;
    private String contentType;

}
