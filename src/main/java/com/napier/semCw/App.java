package com.napier.semCw;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        DatabaseSingleton db = new DatabaseSingleton();

        // Connect to database
        db.connect();

        // Disconnect from database
        db.disconnect();
    }
}
