package me.kimihiqq.postrack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PostrackApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostrackApplication.class, args);
    }

}
