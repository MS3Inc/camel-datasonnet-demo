package com.ms3_inc.demo.camel.recruitment;

import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public ConnectionFactory containedRabbit(@Value("${rabbitmq.host}") String host,
                                             @Value("${rabbitmq.port}") int port,
                                             @Value("${rabbitmq.username}") String user,
                                             @Value("${rabbitmq.password}") String pass) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        factory.setPort(port);
        factory.setUsername(user);
        factory.setPassword(pass);
        return factory;
    }
}
