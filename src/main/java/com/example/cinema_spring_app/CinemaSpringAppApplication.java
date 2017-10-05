package com.example.cinema_spring_app;

import com.example.cinema_spring_app.view.MainFrame;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.awt.*;

//@SpringBootApplication
public class CinemaSpringAppApplication {

	public static void main(String[] args) {

        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(MainFrame.class)
                .headless(false).run(args);

        EventQueue.invokeLater(() -> {
            MainFrame ex = ctx.getBean(MainFrame.class);
            ex.setVisible(true);
        });
	}
}
