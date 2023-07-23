package com.co.app.course.msvcdockerkubernetes.users.services;

import com.co.app.course.msvcdockerkubernetes.users.client.ICourseClientRest;
import com.co.app.course.msvcdockerkubernetes.users.repositories.contracts.IUserRepository;
import com.co.app.course.msvcdockerkubernetes.users.repositories.models.UserEntity;
import com.co.app.course.msvcdockerkubernetes.users.services.contracts.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {


    private final IUserRepository userRepository;


    private final ICourseClientRest courseClientRest;

    @Autowired
    public UserServiceImpl(IUserRepository userRepository, ICourseClientRest courseClientRest) {
        this.userRepository = userRepository;
        this.courseClientRest = courseClientRest;
    }

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
        this.courseClientRest.deleteCourseUser(id);

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserEntity> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> listUserByIds(Iterable<Long> ids) {
        return (List<UserEntity>) userRepository.findAllById(ids);
    }
}
