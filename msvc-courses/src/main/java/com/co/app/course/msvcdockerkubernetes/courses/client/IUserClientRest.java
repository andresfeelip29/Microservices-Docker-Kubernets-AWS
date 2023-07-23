package com.co.app.course.msvcdockerkubernetes.courses.client;

import com.co.app.course.msvcdockerkubernetes.courses.repositories.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "msvc-users", url = "${msvc.users.url}/User")
public interface IUserClientRest {

    @GetMapping("/{id}")
    User getUser(@PathVariable Long id);

    @PostMapping
    User addUser(@RequestBody User user);

    @GetMapping("/UserByIds")
    List<User> listUserByCourse(@RequestParam Iterable<Long> ids);
}
