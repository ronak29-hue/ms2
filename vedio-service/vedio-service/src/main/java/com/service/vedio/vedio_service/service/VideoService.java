package com.service.vedio.vedio_service.service;


import com.service.vedio.vedio_service.dto.CustomPageResponse;
import com.service.vedio.vedio_service.dto.VideoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VideoService {

    VideoDto inset (VideoDto videoDto);

    Page<VideoDto> getAll(Pageable pageable);

    VideoDto getOne(String id);

    void delete(String id);

    VideoDto update(VideoDto videoDto,String id);

    List<VideoDto> seacrh(String keyword);

    VideoDto saveVedioFile(MultipartFile file, String id);

    List<VideoDto> getVedioOfCMultipartFileourse(String courseId);
}
