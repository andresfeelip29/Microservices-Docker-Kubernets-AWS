package com.co.app.course.msvcdockerkubernetes.courses.Services.Contracts;

import com.co.app.course.msvcdockerkubernetes.courses.Repositories.Models.Entities.CourseEntity;

import java.util.List;
import java.util.Optional;

public interface ICourseService {

    List<CourseEntity> findAll();

    Optional<CourseEntity> getCourseById(Long id);

    CourseEntity save(CourseEntity course);

    void delete(Long id);
}
