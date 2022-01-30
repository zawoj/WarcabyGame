package com.server;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * class starting the app
 */
@SpringBootApplication
@ComponentScan("com")
public class App {
    public static void main(final String[] args) {
        SpringApplication.run(App.class, args);
        new TerminalWindow().launchWindow();
    }

    @Bean
    public CommandLineRunner run(GameHistoryRepository gameHistoryRepository) {
        return (args -> {
            ServerCore.getInstance().gameHistoryRepository = gameHistoryRepository;
        });
    }
}
