package com.example.ITBC_Project1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})

@Configuration
public class ItbcProject1Application {

	public static void main(String[] args) {
		SpringApplication.run(ItbcProject1Application.class, args);
	}

}
