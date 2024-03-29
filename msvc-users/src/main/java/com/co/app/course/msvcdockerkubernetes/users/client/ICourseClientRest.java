package com.co.app.course.msvcdockerkubernetes.users.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-courses", url = "${msvc.courses.url}/Course")
public interface ICourseClientRest {

    @DeleteMapping("/CourseUser/{id}")
    void deleteCourseUser(@PathVariable Long id);
}

