package com.service.course.services;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    //multipart--get the file path and in output path- save the path, if you want to change file name with the help of fileName attribute
    String save(MultipartFile file, String outputPath, String fileName);
}
