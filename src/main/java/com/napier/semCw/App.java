package com.napier.semCw;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        DatabaseSingleton db = DatabaseSingleton.getInstance();

        // Connect to database
        db.connect();

        // Collect all languages from the database

        // Disconnect from database
        db.disconnect();
    }
}