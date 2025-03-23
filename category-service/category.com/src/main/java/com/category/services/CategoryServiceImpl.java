package com.category.services;

import com.category.dto.CategoryDto;
import com.category.dto.CustomPageResponse;
import com.category.entites.Category;
import com.category.exception.ResourceNotFoundException;
import com.category.repositories.CategoryRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepo categoryRepo;
    private ModelMapper modelMapper;
    public CategoryServiceImpl(CategoryRepo categoryRepo, ModelMapper modelMapper) {
        this.categoryRepo = categoryRepo;
        this.modelMapper = modelMapper;

    }

    @Override
    public CategoryDto insert(CategoryDto categoryDto) {

       /* Category save=categoryRepo.save(dtoToEntity(categoryDto));

        return entityToDto(save);*/

        String cid= UUID.randomUUID().toString();
        categoryDto.setId(cid);

        categoryDto.setDate(new Date());

        Category category=modelMapper.map(categoryDto,Category.class);
        Category save=categoryRepo.save(category);
        return modelMapper.map(save,CategoryDto.class);
    }

    @Override
    public CustomPageResponse<CategoryDto> getAll(int pageNumber, int pageSize, String sortBy){

        //Sort
        Sort sort= Sort.by(sortBy).ascending();
        //page request
        PageRequest pageRequest=PageRequest.of(pageNumber,pageSize,sort);
        Page<Category> categoryPage=categoryRepo.findAll(pageRequest);
        //store all the date in content
        List<Category> l=categoryPage.getContent();
        List<CategoryDto> dto=l.stream().map(category -> modelMapper.map(category,CategoryDto.class)).collect(Collectors.toList());
        CustomPageResponse<CategoryDto> customPageResponse=new CustomPageResponse<CategoryDto>();
        customPageResponse.setContent(dto);
        customPageResponse.setLast(categoryPage.isLast());
        customPageResponse.setTotalElement(categoryPage.getNumberOfElements());
        customPageResponse.setTotalPages(categoryPage.getTotalPages());
        customPageResponse.setPageNumber(pageNumber);
        customPageResponse.setPageSize(categoryPage.getSize());
        return customPageResponse;
    }

    @Override
    public CategoryDto getOne(String cid) {
        Category category = categoryRepo.findById(cid).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        return entityToDto(category);
    }

    @Override
    public void delete(String cid) {
        Category category=categoryRepo.findById(cid).orElseThrow((()->new ResourceNotFoundException("not found")));
        categoryRepo.delete(category);
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto, String cid) {

        Category category = categoryRepo.findById(cid).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        category.setTitle(categoryDto.getTitle());
        category.setDesc(categoryDto.getDesc());
        Category c=categoryRepo.save(category);
        return entityToDto(c);
    }

   /* @Override
    @Transactional//ye sara kaam hoga tahi commit hoga
    public void addCourseToCategory(String catId, String courseId) {

        //get category
        Category category=categoryRepo.findById(catId).orElseThrow(()-> new ResourceNotFoundException("Category Not found"));

        //get course
        Course course=courseRepo.findById(courseId).orElseThrow(()-> new ResourceNotFoundException("course not found"));

        //course k ander category list me category add ho gyi
        //category k ander jo course the usme couse add ho gya
        category.addCourses(course);
        categoryRepo.save(category);//casecade laga h toh automatic course bhi save ho jayega
        System.out.println("mapping done");
    }
*/
   /* @Override
    @Transactional
    public List<CourseDto> getCoursesOfCategory(String catId) {
        Category category=categoryRepo.findById(catId).orElseThrow(()->new ResourceNotFoundException("Not found"));
        List<Course> courses=category.getCourses();

        return courses.stream().map(course -> modelMapper.map(course,CourseDto.class)).collect(Collectors.toList());
    }
*/
    public CategoryDto entityToDto(Category category)
    {
        CategoryDto categoryDto=modelMapper.map(category,CategoryDto.class);
        return categoryDto;
    }

    public Category dtoToEntity(CategoryDto categoryDto)
    {
        Category category=modelMapper.map(categoryDto,Category.class);
        return category;
    }

}
