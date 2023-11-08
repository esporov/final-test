package ru.liga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import ru.liga.config.CommonConfig;

@SpringBootApplication
@Import(value = {CommonConfig.class})
@EnableFeignClients
public class RestaurantServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(RestaurantServiceApp.class, args);
    }

}
