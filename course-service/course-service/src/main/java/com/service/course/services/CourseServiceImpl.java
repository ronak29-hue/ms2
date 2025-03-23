package com.service.course.services;


import com.service.course.config.AppConstants;
import com.service.course.dto.*;
import com.service.course.entities.Course;
import com.service.course.exception.ResourceNotFoundException;
import com.service.course.repositories.CourseRepo;
import jdk.jfr.Category;
import org.modelmapper.ModelMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.*;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service  //important
public class CourseServiceImpl implements CourseService {


    private CourseRepo courseRepo;
    private ModelMapper modelMapper;
    private FileService fileService;
    private RestTemplate restTemplate;
    private WebClient webClient;

    public CourseServiceImpl(WebClient webClient,CourseRepo courseRepo, ModelMapper modelMapper, FileService fileService, RestTemplate restTemplate) {
        this.webClient=webClient;
        this.courseRepo = courseRepo;
        this.modelMapper = modelMapper;
        this.fileService = fileService;
        this.restTemplate = restTemplate;
    }

    @Override
    public CourseDto create(CourseDto courseDto) {

        String id= UUID.randomUUID().toString();
        courseDto.setId(id);
        Course save=courseRepo.save(this.dtoToEntity(courseDto));
        return entityToDto(save);
    }

   /* @Override
    public CustomPageResponse<CourseDto> getAll(int pageNumber, int pageSize, String sortBy) {

        //sort
        Sort sort=Sort.by(sortBy).ascending();

        //page request
        PageRequest request=PageRequest.of(pageNumber,pageSize,sort);
        Page<Course> coursePage=courseRepo.findAll(request);



        ////store all the date in content
        List<Course> l=coursePage.getContent();
        //List<CategoryDto> dto=l.stream().map(category -> modelMapper.map(category,CategoryDto.class)).collect(Collectors.toList())
        List<CourseDto> dto=l.stream().map(course -> modelMapper.map(course,CourseDto.class)).collect(Collectors.toList());
        CustomPageResponse<CourseDto> customPageResponse=new CustomPageResponse<>();
        customPageResponse.setContent(dto);
        customPageResponse.setLast(coursePage.isLast());
        customPageResponse.setTotalElement(coursePage.getNumberOfElements());
        customPageResponse.setTotalPages(coursePage.getTotalPages());
        customPageResponse.setPageNumber(pageNumber);
        customPageResponse.setPageSize(coursePage.getSize());
        return customPageResponse;
        }
*/

@Override
public Page<CourseDto> getAll(Pageable pageable)
    {

        Page<Course> courses=courseRepo.findAll(pageable);

        //api call to category service to get the category details

        List<CourseDto> dtos=courses.getContent()
                .stream().map(course ->
                        {

                            CourseDto dto=modelMapper.map(course,CourseDto.class);
                            dto.setCategoryDto(getCategoryofCourse(dto.getCategoryId()));
                       //load the videos of all current courses----vedio service
                            dto.setVideos(getVideoOfCourse(dto.getId()));
                            return dto;
                        })
                        .collect(Collectors.toList());

        // if you wantyou can create your page response

      /*  List<CourseDto> newDtos=dtos.stream().map(courseDto -> {*/
            //write thr implementation

/*
            ResponseEntity<List< Category>> exchange=restTemplate.exchange(
                    AppConstants.DEFAULT_BASE_URL+ "/categories/" + courseDto.getCategoryId(),
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<CategoryDto>>()
            {

            }
            List<CategoryDto> body=exchange.getBody();
*/
/*
courseDto.setCategoryDto(getCategoryofCourse(courseDto.getCategoryId()));
return courseDto;
        }).collect(Collectors.toList());
*/

       return new PageImpl<>(dtos,pageable,courses.getTotalElements());

    }

                              @Override

    public CourseDto update(CourseDto dto, String id) {
        Course course = courseRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course not found"));
        course.setTitle(dto.getTitle());
        course.setShortDesc(dto.getShortDesc());
        course.setLDesc(dto.getLDesc());
        course.setPrice(dto.getPrice());
        course.setDiscount(dto.getDiscount());
        course.setLive(dto.isLive());
        Course c=courseRepo.save(course);

        return entityToDto(c);

    }


    @Override
    public void delete(String id) {

        Course course=courseRepo.findById(id).orElseThrow((()->new ResourceNotFoundException("Course not found")));

        courseRepo.delete(course);
    }

    @Override
    public CourseDto getOne(String id) {

        Course course=courseRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Course not found"+id));

        //api call to category service to get category detail


     CourseDto courseDto=modelMapper.map(course,CourseDto.class);
//load category of thecourse ---category service
     courseDto.setCategoryDto(getCategoryofCourse(courseDto.getCategoryId()));
        //load vedio service tp load the videos of this course


          courseDto.setVideos(getVideoOfCourse(course.getId()));

        return courseDto;
    }

    @Override
    public List<CourseDto> searchCourse(String keyword){

        List<Course> courses=courseRepo.findByTitleContainingIgnoreCaseOrShortDescContainingIgnoreCase(keyword,keyword);
        return courses.stream().map(course ->{


               CourseDto dto=modelMapper.map(course,CourseDto.class);
               dto.setCategoryDto(getCategoryofCourse(dto.getCategoryId()));
               //load videos of searched courses---vedeo service
               dto.setVideos(getVideoOfCourse(dto.getId()));
               return dto;
        })
                .collect(Collectors.toList());
    }

    @Override
    public CourseDto saveBanner(MultipartFile file, String cid) {

        Course course = courseRepo.findById(cid).orElseThrow(() -> new ResourceNotFoundException("Not Found"));
        String filePath = fileService.save(file, AppConstants.COURSE_BANNER_UPLOAD_DIR, file.getOriginalFilename());
        course.setBannerName(filePath);
        course.setBannerContentType(file.getContentType());
        return modelMapper.map(courseRepo.save(course),CourseDto.class);
    }

    @Override
    public ResourceContentType getCourseBannerById(String cid) {
        Course course = courseRepo.findById(cid).orElseThrow(() -> new ResourceNotFoundException("Not Found"));
        String bannerPath=course.getBannerName();
        Path path= Paths.get(bannerPath);
        Resource resource=new FileSystemResource(path);
        ResourceContentType resourceContentType=new ResourceContentType();
        resourceContentType.setResource(resource);
        resourceContentType.setContentType(course.getBannerContentType());

        return resourceContentType;
    }


    //we have to map the data of entity to Dto--entity ka data utha kr dto me daal rahe h
    public CourseDto entityToDto(Course course)
    {
        /*CourseDto courseDto=new CourseDto();
        courseDto.setId(course.getId());
        courseDto.setTitle(course.getTitle());
*/
        CourseDto courseDto =modelMapper.map(course,CourseDto.class);
        return courseDto;
    }

    //entity--dto ka data utha kr dto me daal rahe h
    public Course dtoToEntity(CourseDto courseDto)
    {
        /*Course course=new Course();
        course.setId(courseDto.getId());
        course.setTitle(courseDto.getTitle());
*/
        Course course=modelMapper.map(courseDto,Course.class);
        return course;
    }


    //api call for loading category using category id----using restTemplate
    public CategoryDto getCategoryofCourse(String categoryid)
    {
 try {
     ResponseEntity<CategoryDto> exchange = restTemplate.exchange(

             AppConstants.DEFAULT_BASE_URL + "/categories/" + categoryid,

             HttpMethod.GET,
             null,

             CategoryDto.class);
     return exchange.getBody();
 }
 catch (HttpClientErrorException ex)
     {
ex.printStackTrace();
return null;
     }
    }

//call videos service to get vedios of the course
public List<VideoDto> getVideoOfCourse(String courseid)
    {
        List<VideoDto> list= webClient.get()
                .uri(AppConstants.VIDEO_SERVICE_BASE_URL + "/videos/course/{id}", courseid)
                .retrieve()
                .bodyToFlux(VideoDto.class)
                .collect(Collectors.toList())
                .block();

                 return list;
    }


    }
