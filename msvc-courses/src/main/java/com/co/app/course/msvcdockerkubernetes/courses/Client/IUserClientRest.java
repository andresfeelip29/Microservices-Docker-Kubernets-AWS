package com.co.app.course.msvcdockerkubernetes.courses.Client;

import com.co.app.course.msvcdockerkubernetes.courses.Repositories.Models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "msvc-users", url = "localhost:8001/api/v1/User")
public interface IUserClientRest {

    @GetMapping("/{id}")
    User getUser(@PathVariable Long id);

    @PostMapping
    User addUser(@RequestBody User user);

    @GetMapping("/UserByIds")
    List<User> listUserByCourse(@RequestParam Iterable<Long> ids);
}
