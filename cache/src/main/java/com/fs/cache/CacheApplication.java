package com.fs.cache;

import com.fs.cache.domain.entity.User;
import com.fs.cache.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@RequiredArgsConstructor
@EnableJpaAuditing
public class CacheApplication implements ApplicationRunner {
    private final UserRepository userRepository;
    public static void main(String[] args) {
        SpringApplication.run(CacheApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        userRepository.save(User.builder()
                        .name("grage")
                        .email("grage@example.com")
                        .build());
        userRepository.save(User.builder()
                .name("bob")
                .email("bob@example.com")
                .build());
        userRepository.save(User.builder()
                .name("Alice")
                .email("Alice@example.com")
                .build());
        userRepository.save(User.builder()
                .name("Rylen")
                .email("Rylen@example.com")
                .build());

    }
}
