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

        ArrayList<City> cities = db.topNCapitalCitiesInContinent("Europe", 5);
        db.printAllCities(cities);

        // Disconnect from database
        db.disconnect();
    }
}