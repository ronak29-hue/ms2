package com.service.vedio.vedio_service.documents;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Vedio {

    @Id
    private String id;
    private String title;
    private String desc;
    private String filePath;
    private String contentType;
    private String courseId;
}
