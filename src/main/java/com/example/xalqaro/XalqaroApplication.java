package com.example.xalqaro;

import com.example.xalqaro.storage.StorageService;
import com.example.xalqaro.user.Role;
import com.example.xalqaro.user.User;
import com.example.xalqaro.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
public class XalqaroApplication {
    private final PasswordEncoder passwordEncoder;
    public static void main(String[] args) {
        SpringApplication.run(XalqaroApplication.class, args);
    }
    @Bean
    CommandLineRunner runner(UserService userService){
        if(!userService.checkUser("admin")){
            return args -> {
                var admin = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
                        .fullName("Akbar Otaboev")
                        .role(Role.ADMIN)
                        .status(1)
                        .build();
                userService.createUser(admin);
            };
        }else {
            return args -> {};
        }

    };
    @Bean
    CommandLineRunner init(StorageService storageService){
        return args -> {
            storageService.init();
        };
    }

}
