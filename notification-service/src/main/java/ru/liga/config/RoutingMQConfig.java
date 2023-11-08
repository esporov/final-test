package ru.liga.config;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RoutingMQConfig {
    @Bean
    public Declarables myBaseQueue() {
        Queue queueDirectFirst = new Queue("kitchen", false, false, false);
        Queue queueDirectSecond = new Queue("delivery", false, false, false);
        DirectExchange directExchange = new DirectExchange("KitchenDeliveryExchange");
        return new Declarables(queueDirectFirst, queueDirectSecond, directExchange,
                BindingBuilder
                        .bind(queueDirectFirst)
                        .to(directExchange)
                        .with("kitchen"),
                BindingBuilder
                        .bind(queueDirectSecond)
                        .to(directExchange)
                        .with("delivery"));
    }
}
