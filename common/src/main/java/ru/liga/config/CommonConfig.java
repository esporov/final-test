package ru.liga.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(value = "ru.liga.domain.enitity")
@ComponentScan(value = "ru.liga")
public class CommonConfig {
}
