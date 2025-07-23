package com.cafeteria_app;

import com.cafeteria_app.gui.VistaLogin;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;

@SpringBootApplication
public class CafeteriaApplication {

    public static void main(String[] args) {
        // 1. Cargar variables desde el archivo .env
        Dotenv dotenv = Dotenv.load();
        System.setProperty("DATABASE_URL", dotenv.get("DATABASE_URL"));
        System.setProperty("DATABASE_USER", dotenv.get("DATABASE_USER"));
        System.setProperty("DATABASE_PASSWORD", dotenv.get("DATABASE_PASSWORD"));

        ConfigurableApplicationContext contextoSpring = new SpringApplicationBuilder(CafeteriaApplication.class)
                .headless(false)
                .web(WebApplicationType.NONE)
                .run(args);

        SwingUtilities.invokeLater(() -> {
            VistaLogin vistaLogin = contextoSpring.getBean(VistaLogin.class);
            vistaLogin.setVisible(true);
        });

        SpringApplication.run(CafeteriaApplication.class, args);
    }
}
