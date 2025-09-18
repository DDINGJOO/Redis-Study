package com.fs.cache.domain.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    @GetMapping("users/{id}")
    void getUser(@PathVariable Long id)
    {
        // TODO
    }
}
