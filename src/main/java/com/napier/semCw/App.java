package com.napier.semCw;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        DatabaseSingleton db = DatabaseSingleton.getInstance();

        // Connect to database
        db.connect();

        // Collect all countries from the database
        ArrayList<Country> countries = db.getAllCountriesFromDatabase(true);
        db.printAllCountries(countries, "Europe");

        // Disconnect from database
        db.disconnect();
    }
}