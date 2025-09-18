package com.fs.cache.domain.service;


import com.fs.cache.domain.entity.User;
import com.fs.cache.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final RedisTemplate<String, User> redisTemplate;
    private final RedisTemplate<String, Object> objectRedisTemplate;

    public User getUser(final Long id){

        // 1. cache get
//        var cachedUser = redisTemplate.opsForValue().get("users:%d".formatted(id));
        var cachedUser = objectRedisTemplate.opsForValue().get("users:%d".formatted(id));
        if(cachedUser != null){
            return (User)cachedUser;
        }
        // 2. else db get
        var user = userRepository.findById(id).orElseThrow();
        // 3. cache set
        objectRedisTemplate.opsForValue().set("users:%d".formatted(id), user);
        // 4. end
        return user;

    }
}
