package dev.eon.accountmanager;

import dev.eon.accountmanager.adapter.rabbitmq.RabbitMqAdapter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "appAuditor")
public class AccountmanagerApplication {
    @Value("${rabbitmq.host}")
    private String rabbitmqHost;

    @Value("${rabbitmq.username}")
    private String rabbitmqUsername;

    @Value("${rabbitmq.password}")
    private String rabbitmqPassword;

    public static void main(String[] args) {
        SpringApplication.run(AccountmanagerApplication.class, args);
    }

    @SneakyThrows
    @Bean
    public RabbitMqAdapter rabbitMq() {
        return new RabbitMqAdapter(rabbitmqHost, rabbitmqUsername, rabbitmqPassword);
    }
}
