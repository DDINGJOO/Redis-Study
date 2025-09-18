package com.fs.jedischache;


import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

@Component
public class RedisConfig {
    @Bean
    public JedisPool jedisPool() {
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setJmxEnabled(false);
        return new JedisPool(poolConfig, "127.0.0.1", 6379);
    }

}
