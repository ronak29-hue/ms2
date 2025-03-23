package com.service.vedio.vedio_service.repositories;

import com.service.vedio.vedio_service.documents.Vedio;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface VideoRepo extends MongoRepository<Vedio,String>
{
List<Vedio> findByTitleContainingIgnoreCaseOrDescContainingIgnoreCase(String keyword, String decs);

List<Vedio> findByCourseId(String courseId);
}
