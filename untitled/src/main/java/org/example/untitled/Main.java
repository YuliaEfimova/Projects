package org.example.untitled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication(scanBasePackages = {"org.example.untitled", "org.example.untitled.controllers"})
public class Main extends SpringBootServletInitializer {  //
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(Main.class, args);
        try {
            logger.info("OK");
            TelegramBotsApi botsApi = new TelegramBotsApi();
            MyAmazingBot myAmazingBot = new MyAmazingBot();
            botsApi.registerBot(myAmazingBot);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
}
