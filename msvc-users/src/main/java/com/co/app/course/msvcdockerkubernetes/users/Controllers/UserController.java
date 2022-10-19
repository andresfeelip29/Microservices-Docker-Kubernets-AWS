package com.co.app.course.msvcdockerkubernetes.users.Controllers;

import com.co.app.course.msvcdockerkubernetes.users.Repositories.Models.Entities.UserEntity;
import com.co.app.course.msvcdockerkubernetes.users.Services.Contracts.IUserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/User")
public class UserController {

    @Autowired
    IUserService userService;

    @GetMapping("/")
    public List<UserEntity> getUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        Optional<UserEntity> user = userService.getUserById(id);
        return user.isPresent() ? ResponseEntity.ok(user.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody UserEntity user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @PutMapping(name = "/{id}")
    public ResponseEntity<?> updateUser(@RequestBody UserEntity user, @PathVariable Long id) {
        Optional<UserEntity> userTemp = userService.getUserById(id);
        if (userTemp.isPresent()) {
            UserEntity userEntity = userTemp.get();
            userEntity.setEmail(user.getEmail());
            userEntity.setName(user.getName());
            userEntity.setPassword(user.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userEntity));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(name = "/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        Optional<UserEntity> userTemp = userService.getUserById(id);
        if (userTemp.isPresent()) {
            userService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
