package com.co.app.course.msvcdockerkubernetes.users.Services.Contracts;

import com.co.app.course.msvcdockerkubernetes.users.Repositories.Models.Entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<UserEntity> findAll();

    Optional<UserEntity> getUserById(Long id);

    UserEntity save(UserEntity user);

    void delete(Long id);
}


