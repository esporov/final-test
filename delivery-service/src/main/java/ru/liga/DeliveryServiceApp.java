package ru.liga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.liga.config.CommonConfig;

@SpringBootApplication
@EnableTransactionManagement
@Import(value = {CommonConfig.class})
@EnableScheduling
@EnableFeignClients
public class DeliveryServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(DeliveryServiceApp.class, args);
    }
}
