package com.co.app.course.msvcdockerkubernetes.courses.Services.Contracts;

import com.co.app.course.msvcdockerkubernetes.courses.Repositories.Models.Entities.CourseEntity;
import com.co.app.course.msvcdockerkubernetes.courses.Repositories.Models.User;

import java.util.List;
import java.util.Optional;

public interface ICourseService {

    List<CourseEntity> findAll();
    Optional<CourseEntity> getCourseById(Long id);
    Optional<CourseEntity> getCourseWithDetailUsers(Long id);
    CourseEntity save(CourseEntity course);
    void delete(Long id);
    void deleteCourseUserById(Long userId);
    Optional<User> setUser(User user, Long courseId);
    Optional<User> createUser(User user, Long courseId);
    Optional<User> unassignUser (User user, Long courseId);
}
