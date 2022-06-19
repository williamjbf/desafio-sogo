package com.github.williamjbf.desafiosogo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DesafioSogoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesafioSogoApplication.class, args);
    }

    @Bean
    public PasswordEncoder getPasswordEnconder(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

}
