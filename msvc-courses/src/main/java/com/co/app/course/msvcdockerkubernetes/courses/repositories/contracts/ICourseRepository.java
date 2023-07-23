package com.co.app.course.msvcdockerkubernetes.courses.repositories.contracts;

import com.co.app.course.msvcdockerkubernetes.courses.repositories.models.entities.CourseEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ICourseRepository extends CrudRepository<CourseEntity, Long> {


    @Modifying
    @Query("delete from CourseUserEntity cu where cu.userId = ?1")
    void deleteCourseUserById(Long userId);
}
