package com.napier.semCw;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        DatabaseSingleton db = DatabaseSingleton.getInstance();

        // Connect to database
        db.connect();

        // Collect all cities from the database
        ArrayList<City> cities = db.getAllCitiesFromDatabase();
        // Sort all cities from largest population count to smallest
        db.sortCitiesByPopulation(cities);
        db.printAllCities(cities);

        // Disconnect from database
        db.disconnect();
    }
}