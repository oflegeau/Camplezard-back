package com.lezardrieux.back;

import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.FileInputStream;
import java.io.IOException;

@SpringBootApplication
public class BackApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(BackApplication.class, args);

        FileInputStream serviceAccount =
                new FileInputStream("/Users/flegeau/IdeaProjects/Camplezard/Server/siteweb-335f5-firebase-adminsdk-uhk4q-ea1b330ce0.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://liksnet.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);

        System.out.println("ok c'est lancé");
    }

    public static final boolean trace = true;

    public static final int ROLE_ADMIN = 3;
    public static final int ROLE_MANAGER = 2;
    public static final int ROLE_USER = 1;

    public static final int PLACE_ALL = 0;
    public static final int PLACE_STATE = 1;
    public static final int PLACE_OFF = 2;
    public static final int PLACE_BUSY = 3;
    public static final int PLACE_BOOKED = 4;
    public static final int PLACE_FREE = 5;

    public static final int VAR_INVOICE_LOCK = 0;
    public static final int VAR_INVOICE_GEN = 1;
    public static final int VAR_INVOICE_SENT = 2;
    public static final int VAR_INVOICE_PAID = 3;
    public static final double VAR_COEF_NET = 0.78;
    public static final double VAR_COEF_BOSS = 0.45;
}
