package com.fs.jedischache;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    private final JedisPool jedisPool;

    @GetMapping("/users/{id}/email")
    public String getUserEmail(@PathVariable Long id){
        var userEmailUsersKey = "user:%d:email".formatted(id);

        try{
            Jedis jedis = jedisPool.getResource();
            //1. request to cache
            String userEmail = jedis.get(userEmailUsersKey);
            if(userEmail != null){
                return userEmail;
            }
            //2. else db
            userEmail = userRepository.findById(id).map(User::getEmail).orElse("User not found");
            // 3 cache
            jedis.set(userEmailUsersKey, userEmail);
            // 4. end
            return userEmail;

        }catch (Exception e){
            return e.getMessage();
        }

    }
}
