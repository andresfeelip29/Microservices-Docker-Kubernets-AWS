package com.co.app.course.msvcdockerkubernetes.courses.Controllers;

import com.co.app.course.msvcdockerkubernetes.courses.Repositories.Models.Entities.CourseEntity;
import com.co.app.course.msvcdockerkubernetes.courses.Repositories.Models.User;
import com.co.app.course.msvcdockerkubernetes.courses.Services.Contracts.ICourseService;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

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
        //Optional<CourseEntity> course = courseService.getCourseById(id);
        Optional<CourseEntity> course = courseService.getCourseWithDetailUsers(id);
        return course.isPresent() ? ResponseEntity.ok(course.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<?> addCourse(@Valid @RequestBody CourseEntity course, BindingResult result) {
        if (result.hasErrors()) {
            return validate(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.save(course));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourse(@Valid @RequestBody CourseEntity course, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return validate(result);
        }
        Optional<CourseEntity> courseTemp = courseService.getCourseById(id);
        if (courseTemp.isPresent()) {
            CourseEntity courseEntity = courseTemp.get();
            courseEntity.setName(course.getName());
            return ResponseEntity.status(HttpStatus.CREATED).body(courseService.save(courseEntity));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/assignUser/{courseId}")
    public ResponseEntity<?> setUser(@RequestBody User user, @PathVariable Long courseId) {
        Optional<User> o;
        try {
            o = courseService.setUser(user, courseId);
        } catch (FeignException e) {
            return ResponseEntity.
                    status(HttpStatus.NOT_FOUND).
                    body(Collections.singletonMap("mensaje", "No existe usuario por id o error por la comunicacion: " + e.getMessage()));
        }

        if (o.isPresent()) {

            return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/createUser/{courseId}")
    public ResponseEntity<?> createUser(@RequestBody User user, @PathVariable Long courseId) {
        Optional<User> o;
        try {
            o = courseService.createUser(user, courseId);
        } catch (FeignException e) {
            return ResponseEntity.
                    status(HttpStatus.NOT_FOUND).
                    body(Collections.singletonMap("mensaje", "No se pudo crear el usuario o error en la comunicacion : " + e.getMessage()));
        }
        if (o.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/unassignUser/{courseId}")
    public ResponseEntity<?> unassignUser(@RequestBody User user, @PathVariable Long courseId) {
        Optional<User> o;
        try {
            o = courseService.unassignUser(user, courseId);
        } catch (FeignException e) {
            return ResponseEntity.
                    status(HttpStatus.NOT_FOUND).
                    body(Collections.singletonMap("mensaje", "No se pudo crear el usuario o error en la comunicacion : " + e.getMessage()));
        }
        if (o.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(o.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id) {
        Optional<CourseEntity> courseTemp = courseService.getCourseById(id);
        if (courseTemp.isPresent()) {
            courseService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<Map<String, String>> validate(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
}
