package com.service.vedio.vedio_service.service;

import com.service.vedio.vedio_service.documents.Vedio;
import com.service.vedio.vedio_service.dto.VideoDto;
import com.service.vedio.vedio_service.exception.ResourceNotFoundException;
import com.service.vedio.vedio_service.repositories.VideoRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VedioServiceImpl implements VideoService{

    private VideoRepo videoRepo;
    private ModelMapper modelMapper;

    @Autowired
    private CourseService courseService;

    public VedioServiceImpl(VideoRepo videoRepo, ModelMapper modelMapper) {
        this.videoRepo = videoRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public VideoDto inset(VideoDto videoDto) {

        String id= UUID.randomUUID().toString();
        videoDto.setId(id);

        Vedio video=modelMapper.map(videoDto, Vedio.class);
        Vedio save=videoRepo.save(video);
        return modelMapper.map(save,VideoDto.class);
    }

    @Override
    public Page<VideoDto> getAll(Pageable pageable) {

        Page<Vedio> video=videoRepo.findAll(pageable);

        List<VideoDto> dtos=video.getContent()
                     .stream()
                     .map(vedio -> modelMapper.map(vedio,VideoDto.class))
                     .collect(Collectors.toList());
        return new PageImpl<>(dtos,pageable,video.getTotalElements());
    }


    @Override
    public VideoDto getOne(String id) {
        Vedio video=videoRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Vedio not found "+id));
     VideoDto videoDto= modelMapper.map(video,VideoDto.class);
     videoDto.setCourseDto(courseService.getCourseById(videoDto.getCourseId()));
    return videoDto;
    }

    @Override
    public void delete(String id) {
     Vedio video=videoRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Vedio not found"+id));
     videoRepo.delete(video);
    }

    @Override
    public VideoDto update(VideoDto videoDto, String id) {

        Vedio video=videoRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Vedio not found"+id));
        video.setTitle(videoDto.getTitle());
        video.setDesc(videoDto.getDesc());
        video.setFilePath(videoDto.getFilePath());
        Vedio save=videoRepo.save(video);
        return modelMapper.map(save,VideoDto.class);
    }

    @Override
    public List<VideoDto> seacrh(String keyword) {

        List<Vedio> vedios=videoRepo.findByTitleContainingIgnoreCaseOrDescContainingIgnoreCase(keyword,keyword);

        return vedios.stream()
                .map(vedio -> modelMapper.map(vedio,VideoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public VideoDto saveVedioFile(MultipartFile file, String id) {
        return null;
    }

    @Override
    public List<VideoDto> getVedioOfCMultipartFileourse(String courseId) {
        return videoRepo.findByCourseId(courseId)
              .stream()
                .map(vedio -> modelMapper.map(vedio,VideoDto.class))
                .collect(Collectors.toList());
    }
}
