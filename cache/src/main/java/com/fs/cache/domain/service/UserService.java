package com.fs.cache.domain.service;


import com.fs.cache.domain.entity.User;
import com.fs.cache.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public User getUser(final Long id){

        return userRepository.findById(id).orElseThrow();

    }
}
