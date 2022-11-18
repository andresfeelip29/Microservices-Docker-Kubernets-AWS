package com.co.app.course.msvcdockerkubernetes.users.Repositories.Contracts;

import com.co.app.course.msvcdockerkubernetes.users.Repositories.Models.Entities.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IUserRepository extends CrudRepository<UserEntity, Long> {

    /*Using declarative keywords for JPA interpreters */
    Optional<UserEntity> findByEmail(String email);

    /*Using Query*/
    @Query("select u from UserEntity u where u.email = ?1")
    Optional<UserEntity> findByEmailUsingQuery(String email);

    /*Using declarative keywords for JPA interpreters */
    boolean existsByEmail(String email);


}
