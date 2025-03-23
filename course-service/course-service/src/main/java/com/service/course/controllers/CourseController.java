package com.service.course.controllers;


import com.service.course.config.AppConstants;
import com.service.course.dto.CourseDto;
import com.service.course.dto.CustomMessage;
import com.service.course.dto.CustomPageResponse;
import com.service.course.dto.ResourceContentType;
import com.service.course.services.CourseService;
import com.service.course.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/*--we have to apply manually at every page
@SecurityRequirement(
        name="JWTScheme"
)//use the from the project Config
*/
@RestController
@RequestMapping("/api/v1/courses")//standard way for url
public class CourseController {


    private FileService fileService;

    private CourseService courseService;

    public CourseController(FileService fileService, CourseService courseService) {
        this.fileService = fileService;
        this.courseService = courseService;
    }


    @PostMapping
    public ResponseEntity<?> insert(@RequestBody CourseDto courseDto)
    {
        CourseDto courseDto1=courseService.create(courseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(courseDto1);
    }

    //get all courses
    @GetMapping




    public ResponseEntity<Page<CourseDto>> getAll(Pageable pageable)

    {
        return ResponseEntity.ok(courseService.getAll(pageable));
    }

    //get single courses
    @GetMapping("/{id}")
    public CourseDto getOne(@PathVariable String id)
    {
        return courseService.getOne(id);
    }

    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomMessage> delete(@PathVariable String id)
    {
        courseService.delete(id);
        CustomMessage customMessage=new CustomMessage();
        customMessage.setSuccess(true);
        customMessage.setMessage("course deleted");

        return ResponseEntity.status(HttpStatus.OK).body(customMessage);
    }

    @PutMapping("/{id}")
    public CourseDto update(@PathVariable String id,@RequestBody CourseDto courseDto )
    {
        return courseService.update(courseDto,id);
    }

    @GetMapping("/search")
    public ResponseEntity<List<CourseDto>> search(@RequestParam String keyword)
    {
        return ResponseEntity.ok(courseService.searchCourse(keyword));
    }


    //api for image
    //fetch the file
    @PostMapping("/{cid}/banners")
    public ResponseEntity<?> uploadBanner(
            @PathVariable String cid,
            @RequestParam("banner") MultipartFile banner
            ) throws IOException
    {
        //apply checks here
        String contentType=banner.getContentType();
        if(contentType==null)
        {
            contentType="image/png";
        }
        else if (contentType.equalsIgnoreCase("image/png") || contentType.equalsIgnoreCase("image/jpeg"))
        {

        }
        else {
            CustomMessage customMessage=new CustomMessage();
            customMessage.setSuccess(false);
            customMessage.setMessage("invalid files");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customMessage);
        }

        //meta data-- file information
        System.out.println(banner.getOriginalFilename());
        System.out.println(banner.getName());
        System.out.println(banner.getContentType());
        System.out.println(banner.getSize());

        //save the file-- this is logic er cannot write here controller only accept the request
       // fileService.save(banner,AppConstants.COURSE_BANNER_UPLOAD_DIR,banner.getOriginalFilename());

        CourseDto courseDto=courseService.saveBanner(banner,cid);

        return ResponseEntity.ok(courseDto);
    }

    //server banner-- display img on server
    @GetMapping("/{cid}/banners")
    public ResponseEntity<Resource> serverBanner(@PathVariable String cid)
    {


        ResourceContentType resource=courseService.getCourseBannerById(cid);
        return  ResponseEntity.ok().contentType(MediaType.parseMediaType(resource.getContentType())).body(resource.getResource());//we have used parse method beacuse it get string value and we need .jpegor png
    }
}
