package com.co.app.course.msvcdockerkubernetes.courses.Repositories.Models.Entities;

import com.co.app.course.msvcdockerkubernetes.courses.Repositories.Models.User;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cursos")
public class CourseEntity {

    public CourseEntity() {
        courseUserEntities = new ArrayList<>();
        users = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String name;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "curso_id")
    private List<CourseUserEntity> courseUserEntities;

    @Transient
    private List<User> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addCourseUser(CourseUserEntity courseUserEntity) {
        this.courseUserEntities.add(courseUserEntity);
    }

    public void removeCourseUser(CourseUserEntity courseUserEntity) {
        this.courseUserEntities.remove(courseUserEntity);
    }

    public List<CourseUserEntity> getCourseUserEntities() {
        return courseUserEntities;
    }

    public void setCourseUserEntities(List<CourseUserEntity> courseUserEntities) {
        this.courseUserEntities = courseUserEntities;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
