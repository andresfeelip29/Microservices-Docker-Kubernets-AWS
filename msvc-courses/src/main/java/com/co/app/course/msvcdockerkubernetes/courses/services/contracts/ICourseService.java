package com.co.app.course.msvcdockerkubernetes.courses.services.contracts;

import com.co.app.course.msvcdockerkubernetes.courses.repositories.models.entities.CourseEntity;
import com.co.app.course.msvcdockerkubernetes.courses.repositories.models.User;

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
