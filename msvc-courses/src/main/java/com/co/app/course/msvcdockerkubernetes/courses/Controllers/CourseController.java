package com.co.app.course.msvcdockerkubernetes.courses.Controllers;

import com.co.app.course.msvcdockerkubernetes.courses.Repositories.Models.Entities.CourseEntity;
import com.co.app.course.msvcdockerkubernetes.courses.Services.Contracts.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/Course")
public class CourseController {
    @Autowired
    private ICourseService courseService;

    @GetMapping("/")
    public List<CourseEntity> getCourses() {
        return courseService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCourse(@PathVariable Long id) {
        Optional<CourseEntity> course = courseService.getCourseById(id);
        return course.isPresent() ? ResponseEntity.ok(course.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> addCourse(@RequestBody CourseEntity course) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.save(course));
    }

    @PutMapping(name = "/{id}")
    public ResponseEntity<?> updateCourse(@RequestBody CourseEntity user, @PathVariable Long id) {
        Optional<CourseEntity> courseTemp = courseService.getCourseById(id);
        if (courseTemp.isPresent()) {
            CourseEntity courseEntity = courseTemp.get();
            courseEntity.setName(user.getName());
            return ResponseEntity.status(HttpStatus.CREATED).body(courseService.save(courseEntity));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(name = "/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        Optional<CourseEntity> courseTemp = courseService.getCourseById(id);
        if (courseTemp.isPresent()) {
            courseService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
