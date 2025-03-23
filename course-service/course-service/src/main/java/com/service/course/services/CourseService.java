package com.service.course.services;


import com.service.course.dto.CourseDto;
import com.service.course.dto.CustomPageResponse;
import com.service.course.dto.ResourceContentType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CourseService {

    CourseDto create(CourseDto courseDto);

    Page<CourseDto> getAll(Pageable pageable);

    CourseDto update(CourseDto dto, String id);

    void delete(String id);

    CourseDto getOne(String id);

    List<CourseDto> searchCourse(String keyword);

    //save the banner
    public CourseDto saveBanner(MultipartFile file, String cid);

    public ResourceContentType getCourseBannerById(String cid);
}
