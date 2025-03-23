package com.service.course.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileServiceImpl implements FileService{
    @Override
    public String save(MultipartFile file, String outputPath,String fileName) {

        //logic

        Path path= Paths.get(outputPath);

        //create output folder if not exists
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //  /files/abc.png
        //path ko join kiya h
        Path filePath=Paths.get(path.toString(),file.getOriginalFilename());
        System.out.println(filePath);

        //file writes
        try {
            Files.copy(file.getInputStream(),filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return filePath.toString();
    }
}
