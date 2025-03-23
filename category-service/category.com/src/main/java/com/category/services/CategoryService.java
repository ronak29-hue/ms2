package com.category.services;

import com.category.dto.CategoryDto;
import com.category.dto.CustomPageResponse;

public interface CategoryService {


        CategoryDto insert (CategoryDto categoryDto);


        CustomPageResponse<CategoryDto> getAll(int pageNumber, int pageSize, String sortBy);

        CategoryDto getOne(String cid);

        void delete(String cid);

        CategoryDto update(CategoryDto categoryDto,String cid);

        //search

        //mapping
       // public void addCourseToCategory(String catId,String courseId);

        // get the list of courses in category
        //List<CourseDto> getCoursesOfCategory(String catId);



}

