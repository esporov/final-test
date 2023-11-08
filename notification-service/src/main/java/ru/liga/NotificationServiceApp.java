package ru.liga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import ru.liga.config.CommonConfig;

@SpringBootApplication
@Import(value = {CommonConfig.class})
public class NotificationServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApp.class, args);
    }
}
