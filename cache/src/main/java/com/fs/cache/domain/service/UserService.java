package com.fs.cache.domain.service;


import com.fs.cache.domain.entity.RedisHashUser;
import com.fs.cache.domain.entity.User;
import com.fs.cache.domain.repository.RedisHashUserRepository;
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
    private final RedisHashUserRepository redisHashUserRepository;

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


    public RedisHashUser getUser2(final Long id){
        //Using Redis Hash User Repository
        //redis에 값이 있으면 리턴
        //else db

        return redisHashUserRepository.findById(id).orElseGet(() -> {
            User user = userRepository.findById(id).orElseThrow();
            return redisHashUserRepository.save(RedisHashUser.builder()
                            .email(user.getEmail())
                            .id(user.getId())
                            .name(user.getName())
                            .createdAt(user.getCreatedAt())
                            .updatedAt(user.getUpdatedAt())
                    .build());
        });
    }

}
