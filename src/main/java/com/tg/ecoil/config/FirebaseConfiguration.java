package com.tg.ecoil.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfiguration {

    @Value("${firebase.config.path:./serviceAccountKey.json}")
    private String serviceAccountKeyPath;

    @PostConstruct
    public void init() {
        try (InputStream serviceAccount = new FileInputStream(serviceAccountKeyPath)) {
            final var options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);

        } catch (IOException e) {
            throw new IllegalStateException("Failed to initialize Firebase", e);
        }
    }
}