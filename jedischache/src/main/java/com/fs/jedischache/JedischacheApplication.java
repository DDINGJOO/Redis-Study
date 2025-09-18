package com.fs.jedischache;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class JedischacheApplication implements ApplicationRunner {

    private final UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(JedischacheApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        userRepository.save(User.builder()
                .name("greg")
                .email("grege@example.com")
                .build());
        userRepository.save(User.builder()
                .name("tony")
                .email("tony@example.com")
                .build());
        userRepository.save(User.builder()
                .name("bob")
                .email("bob@example.com")
                .build());
        userRepository.save(User.builder()
                .name("Ryan")
                .email("Ryan@example.com")
                .build());

    }
}
