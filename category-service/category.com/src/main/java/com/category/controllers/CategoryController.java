package com.category.controllers;

import com.category.config.AppConstants;
import com.category.dto.CategoryDto;
import com.category.dto.CustomMessage;
import com.category.dto.CustomPageResponse;
import com.category.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
//@CrossOrigin(origins = "*")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    //create category

    @PostMapping
    public ResponseEntity<?> create(

            @Valid @RequestBody CategoryDto categoryDto
            // BindingResult result
    )
    {
       /* if(result.hasErrors())
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid Data");
        }*/
          CategoryDto res=categoryService.insert(categoryDto);
          //change the status-201 for create so we use responseentity
          return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    //get all category
    //for pagination-pagesize and page number---not hard code this value need to create the Appconstants classfor configure the value
    @GetMapping
    public CustomPageResponse<CategoryDto>getAll(
            @RequestParam(value = "pageNumber",required = false,defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int pageNumber,
            @RequestParam(value="pageSize",required = false,defaultValue =AppConstants.DEFAULT_PAGE_SIZE) int pageSize,
            @RequestParam(value = "sortBy",required = false,defaultValue = AppConstants.DEFAULT_SORT_BY) String sortBy
    )

    {
        return categoryService.getAll(pageNumber,pageSize,sortBy);
    }

    //get single category

    @GetMapping("/{cid}")
    public CategoryDto getSingle(@PathVariable String cid){
        return categoryService.getOne(cid);
    }

    //delete

    @DeleteMapping("/{cid}")
    public ResponseEntity<CustomMessage> delete(@PathVariable String cid)
{
    categoryService.delete(cid);
    CustomMessage customMessage=new CustomMessage();
    customMessage.setSuccess(true);
    customMessage.setMessage("category deleted");

    return ResponseEntity.status(HttpStatus.OK).body(customMessage);
}
    //update

    @PutMapping("/{cid}")
    public CategoryDto update(
            @PathVariable String cid,
            @RequestBody CategoryDto categoryDto
    )
    {
   return categoryService.update(categoryDto,cid);
    }

/*@PostMapping("/{catId}/courses/{courseId}")
    public ResponseEntity<CustomMessage> addCourseToCategory(
            @PathVariable String catId,
            @PathVariable String courseId
)
{
    categoryService.addCourseToCategory(catId,courseId);
    CustomMessage customMessage=new CustomMessage();
    customMessage.setMessage("Relationship done");
    customMessage.setSuccess(true);
    return  ResponseEntity.ok(customMessage);
}*/

/*@GetMapping("/{catId}/courses")
    public ResponseEntity<List<CourseDto>> getCourseesOfCategory(@PathVariable String catId)
{
    return ResponseEntity.ok(categoryService.getCoursesOfCategory(catId));
}*/
}
