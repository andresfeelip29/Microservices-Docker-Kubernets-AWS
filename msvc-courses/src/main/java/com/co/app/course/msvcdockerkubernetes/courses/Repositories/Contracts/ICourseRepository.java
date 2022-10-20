package com.co.app.course.msvcdockerkubernetes.courses.Repositories.Contracts;

import com.co.app.course.msvcdockerkubernetes.courses.Repositories.Models.Entities.CourseEntity;
import org.springframework.data.repository.CrudRepository;

public interface ICourseRepository extends CrudRepository<CourseEntity, Long> {

}
