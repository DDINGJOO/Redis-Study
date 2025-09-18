package com.fs.cache.domain.repository;


import com.fs.cache.domain.entity.RedisHashUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisHashUserRepository extends CrudRepository<RedisHashUser, Long> {
    RedisHashUser findByName(String name);
    RedisHashUser findByEmail(String email);
    // ...
}
