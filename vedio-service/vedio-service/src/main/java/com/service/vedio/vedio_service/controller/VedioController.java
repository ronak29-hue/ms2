package com.service.vedio.vedio_service.controller;

import com.service.vedio.vedio_service.dto.CustomMessage;
import com.service.vedio.vedio_service.dto.VideoDto;
import com.service.vedio.vedio_service.service.VideoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/videos")

public class VedioController {

    private VideoService videoService;

    public VedioController(VideoService videoService) {
        this.videoService = videoService;
    }

    //create
    @PostMapping
    public ResponseEntity<?> create(@RequestBody VideoDto videoDto)
    {
        VideoDto res=videoService.inset(videoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    //getall
    @GetMapping
    public ResponseEntity<Page<VideoDto>> getAll(Pageable pageable)
    {
        return ResponseEntity.ok(videoService.getAll(pageable));
    }

    //getOne
    @GetMapping("/{id}")
    public ResponseEntity<VideoDto> getOne(@PathVariable String id)
    {
        return ResponseEntity.ok(videoService.getOne(id));
    }

    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomMessage> delete(@PathVariable String id)
    {
        videoService.delete(id);
        CustomMessage customMessage=new CustomMessage();
        customMessage.setSuccess(true);
        customMessage.setMessage("category deleted");

        return ResponseEntity.status(HttpStatus.OK).body(customMessage);
    }
    @PutMapping("/{id}")
    public ResponseEntity<VideoDto> update(@PathVariable String id, @RequestBody VideoDto videoDto)
    {
        return ResponseEntity.ok(videoService.update(videoDto,id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<VideoDto>> search(@RequestParam String keyword)
    {
        return ResponseEntity.ok(videoService.seacrh(keyword));
    }

    @GetMapping("/course/{courseId}")
    public List<VideoDto> getAllVediosOfCourse(@PathVariable String courseId)
        {
            return this.videoService.getVedioOfCMultipartFileourse(courseId);
        }
}
