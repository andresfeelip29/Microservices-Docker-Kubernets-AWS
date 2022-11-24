package com.co.app.course.msvcdockerkubernetes.courses.Clients;

import com.co.app.course.msvcdockerkubernetes.courses.Repositories.Models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "msvc-users", url = "localhost:8001")
public interface IUserClientRest {

    @GetMapping("/{id}")
    User getUser(@PathVariable Long id);

    @PostMapping
    User addUser(@RequestBody User user);
}
