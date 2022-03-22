package com.napier.semCw;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        DatabaseSingleton db = DatabaseSingleton.getInstance();

        // Connect to database
        if(args.length < 1) {
            db.connect("localhost:33060", 30000);
        } else {
            db.connect(args[0], Integer.parseInt(args[1]));
        }

        // Collect all languages from the database
        ArrayList<Language> languages = db.getAllLanguagesFromDatabase();
        db.printAllLanguages(languages);

        // Disconnect from database
        db.disconnect();
    }
}