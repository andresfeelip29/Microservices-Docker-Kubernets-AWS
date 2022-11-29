package com.co.app.course.msvcdockerkubernetes.users.Controllers;

import com.co.app.course.msvcdockerkubernetes.users.Repositories.Models.Entities.UserEntity;
import com.co.app.course.msvcdockerkubernetes.users.Services.Contracts.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

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
    public ResponseEntity<?> addUser(@Valid @RequestBody UserEntity user, BindingResult result) {

        if (result.hasErrors()) {
            return validate(result);
        }

        if (!user.getEmail().isEmpty() && userService.getUserByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().
                    body(Collections.
                            singletonMap("error", "Ya existe un usuario con ese email"));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserEntity user, BindingResult result, @PathVariable Long id) {


        if (result.hasErrors()) {
            return validate(result);
        }

        Optional<UserEntity> userTemp = userService.getUserById(id);


        if (userTemp.isPresent()) {
            UserEntity userEntity = userTemp.get();

            if (!user.getEmail().isEmpty() && userService.getUserByEmail(user.getEmail()).isPresent() && !user.getEmail().equalsIgnoreCase(userEntity.getEmail())) {
                return ResponseEntity.badRequest().
                        body(Collections.
                                singletonMap("error", "Ya existe un usuario con ese email"));
            }


            userEntity.setEmail(user.getEmail());
            userEntity.setName(user.getName());
            userEntity.setPassword(user.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userEntity));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        Optional<UserEntity> userTemp = userService.getUserById(id);
        if (userTemp.isPresent()) {
            userService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/UserByIds")
    public ResponseEntity<?> getUsersByCourse(@RequestParam List<Long> ids) {
        return ResponseEntity.ok(userService.listUserByIds(ids));
    }

    private ResponseEntity<Map<String, String>> validate(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }

}
