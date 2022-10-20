package com.co.app.course.msvcdockerkubernetes.courses.Repositories.Models.Entities;

import javax.persistence.*;

@Entity
@Table(name = "cursos")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

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
}
