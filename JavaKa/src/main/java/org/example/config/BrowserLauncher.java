package org.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.net.URI;

@Component
public class BrowserLauncher implements CommandLineRunner {

    @Value("${server.port}")
    private int serverPort;

    @Override
    public void run(String... args) throws Exception {
        String url = "http://localhost:" + serverPort + "/login";
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            Desktop.getDesktop().browse(new URI(url));
        }
    }
}
