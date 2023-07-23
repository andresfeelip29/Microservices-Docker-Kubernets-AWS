package com.co.app.course.msvcdockerkubernetes.courses.repositories.models.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "cursos_usuarios")
public class CourseUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id", unique = true)
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(!(obj instanceof CourseUserEntity)){
            return false;
        }

        CourseUserEntity o = (CourseUserEntity) obj;
        return this.userId != null && this.userId.equals(o.getUserId());
    }
}
