package com.co.app.course.msvcdockerkubernetes.users.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-courses", url = "msvc-courses:8002/api/v1/Course")
public interface ICourseClientRest {

    @DeleteMapping("/CourseUser/{id}")
    void deleteCourseUser(@PathVariable Long id);
}

