package com.co.app.course.msvcdockerkubernetes.users.Repositories.Contracts;

import com.co.app.course.msvcdockerkubernetes.users.Repositories.Models.Entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface IUserRepository extends CrudRepository<UserEntity, Long> {


}
