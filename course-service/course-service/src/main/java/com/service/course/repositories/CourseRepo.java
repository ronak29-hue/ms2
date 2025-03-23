package com.service.course.repositories;


import com.service.course.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepo extends JpaRepository<Course,String> {

    Optional<Course> findByTitle(String title);

    List<Course> findByLive(boolean live);

    //search the courses by title

   List<Course> findByTitleContainingIgnoreCaseOrShortDescContainingIgnoreCase(String k1,String k2);
}
