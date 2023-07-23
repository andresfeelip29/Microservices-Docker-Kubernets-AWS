package com.co.app.course.msvcdockerkubernetes.users.services.contracts;

import com.co.app.course.msvcdockerkubernetes.users.repositories.models.UserEntity;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<UserEntity> findAll();

    Optional<UserEntity> getUserById(Long id);

    UserEntity save(UserEntity user);

    void delete(Long id);
    Optional<UserEntity> getUserByEmail(String email);
    List<UserEntity>  listUserByIds(Iterable<Long> ids);
}


