package com.co.app.course.msvcdockerkubernetes.courses.services;

import com.co.app.course.msvcdockerkubernetes.courses.client.IUserClientRest;
import com.co.app.course.msvcdockerkubernetes.courses.repositories.contracts.ICourseRepository;
import com.co.app.course.msvcdockerkubernetes.courses.repositories.models.entities.CourseEntity;
import com.co.app.course.msvcdockerkubernetes.courses.repositories.models.entities.CourseUserEntity;
import com.co.app.course.msvcdockerkubernetes.courses.repositories.models.User;
import com.co.app.course.msvcdockerkubernetes.courses.services.contracts.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements ICourseService {


    private final ICourseRepository courseRepository;

    private final IUserClientRest client;

    @Autowired
    public CourseServiceImpl(ICourseRepository courseRepository, IUserClientRest client) {
        this.courseRepository = courseRepository;
        this.client = client;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseEntity> findAll() {
        return (List<CourseEntity>) courseRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CourseEntity> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    @Transactional
    public CourseEntity save(CourseEntity course) {
        return courseRepository.save(course);
    }

    @Override
    @Transactional(readOnly = true)
    public void delete(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteCourseUserById(Long userId) {
        courseRepository.deleteCourseUserById(userId);
    }

    @Override
    @Transactional
    public Optional<User> setUser(User user, Long courseId) {

        Optional<CourseEntity> o = courseRepository.findById(courseId);
        if (o.isPresent()) {
            User userMsvc = client.getUser(user.getId());
            CourseEntity course = o.get();
            CourseUserEntity courseUserEntity = new CourseUserEntity();

            courseUserEntity.setUserId(userMsvc.getId());
            course.addCourseUser(courseUserEntity);
            courseRepository.save(course);
            return Optional.of(userMsvc);
        }

        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<User> createUser(User user, Long courseId) {

        Optional<CourseEntity> o = courseRepository.findById(courseId);
        if (o.isPresent()) {
            User userMsvc = client.addUser(user);
            CourseEntity course = o.get();
            CourseUserEntity courseUserEntity = new CourseUserEntity();

            courseUserEntity.setUserId(userMsvc.getId());
            course.addCourseUser(courseUserEntity);
            courseRepository.save(course);
            return Optional.of(userMsvc);
        }

        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<User> unassignUser(User user, Long courseId) {
        Optional<CourseEntity> o = courseRepository.findById(courseId);
        if (o.isPresent()) {
            User userMsvc = client.getUser(user.getId());
            CourseEntity course = o.get();
            CourseUserEntity courseUserEntity = new CourseUserEntity();

            courseUserEntity.setUserId(userMsvc.getId());
            course.removeCourseUser(courseUserEntity);
            courseRepository.save(course);
            return Optional.of(userMsvc);
        }

        return Optional.empty();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CourseEntity> getCourseWithDetailUsers(Long id) {
        Optional<CourseEntity> o = courseRepository.findById(id);
        if (o.isPresent()) {
            CourseEntity course = o.get();
            if (!course.getCourseUserEntities().isEmpty()) {
                List<Long> ids = course.getCourseUserEntities()
                        .stream()
                        .map(CourseUserEntity::getUserId)
                        .collect(Collectors.toList());
                course.setUsers(client.listUserByCourse(ids));
            }
            return Optional.of(course);
        }
        return Optional.empty();
    }
}
