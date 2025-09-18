package com.fs.cache.domain.controller;


import com.fs.cache.domain.entity.User;
import com.fs.cache.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users/{id}")
    User getUser(@PathVariable Long id)
    {
        //
        User user = userService.getUser(id);
        return user;
    }
}
