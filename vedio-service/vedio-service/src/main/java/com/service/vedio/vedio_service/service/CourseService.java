package com.service.vedio.vedio_service.service;

import com.service.vedio.vedio_service.dto.CourseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "course-service",url = "http://course-service:9098/api/v1")
public interface CourseService {

    @GetMapping("/courses/{cid}")
    CourseDto getCourseById(@PathVariable String cid);
}
