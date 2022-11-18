package com.co.app.course.msvcdockerkubernetes.users.Services;

import com.co.app.course.msvcdockerkubernetes.users.Repositories.Contracts.IUserRepository;
import com.co.app.course.msvcdockerkubernetes.users.Repositories.Models.Entities.UserEntity;
import com.co.app.course.msvcdockerkubernetes.users.Services.Contracts.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {


    @Autowired
    private IUserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public List<UserEntity> findAll() {
        return (List<UserEntity>) this.userRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<UserEntity> getUserById(Long id) {
        return this.userRepository.findById(id);
    }

    @Transactional
    @Override
    public UserEntity save(UserEntity user) {
        return this.userRepository.save(user);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        this.userRepository.deleteById(id);
    }
    @Override
    @Transactional(readOnly = true)
    public Optional<UserEntity> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
