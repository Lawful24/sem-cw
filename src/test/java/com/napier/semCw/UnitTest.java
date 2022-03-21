package com.napier.semCw;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UnitTest {
    static DatabaseSingleton db;

    @BeforeAll
    static void init() {
        db = DatabaseSingleton.getInstance();
        db.connect("localhost:33060", 30000);
    }

    @Test
    void getAllCountriesFromDatabaseTestNotNull() {
        assertNotNull(db.getAllCountriesFromDatabase());
    }

    @Test
    void getAllCountriesFromDatabaseTestContainsNull() {
        List<Country> results = db.getAllCountriesFromDatabase();
        assertFalse(results.contains(null));
    }

    @Test
    void getAllCountriesFromDatabaseTestContainsDefaultObject() {
        List<Country> results = db.getAllCountriesFromDatabase();
        for (Country c : results) {
            assertNotNull(c.code);
            assertNotNull(c.name);
            assertNotNull(c.continent);
            assertNotNull(c.region);
            assertNotNull(c.language);
        }
    }

    @Test
    void getAllCitiesFromDatabaseTestNotNull() {
        assertNotNull(db.getAllCitiesFromDatabase());
    }

    @Test
    void getAllCitiesFromDatabaseTestContainsNull() {
        List<City> results = db.getAllCitiesFromDatabase();
        assertFalse(results.contains(null));
    }

    @Test
    void getAllCitiesFromDatabaseTestContainsDefaultObject() {
        List<City> results = db.getAllCitiesFromDatabase();
        for (City c : results) {
            assertNotNull(c.getCountryCode());
            assertNotNull(c.getName());
            assertNotNull(c.getDistrictName());
            assertTrue(c.getId() >= 0);
            assertTrue(c.getPopulation() >= 0);
        }
    }

    @Test
    void getAllLanguagesFromDatabaseTestNotNull() {
        assertNotNull(db.getAllLanguagesFromDatabase());
    }

    @Test
    void getAllLanguagesFromDatabaseTestContainsNull() {
        List<Language> results = db.getAllLanguagesFromDatabase();
        assertFalse(results.contains(null));
    }

    @Test
    void getAllLanguagesFromDatabaseTestContainsDefaultObject() {
        List<Language> results = db.getAllLanguagesFromDatabase();
        for (Language l : results) {
            assertNotNull(l.getCountryCode());
            assertNotNull(l.getLanguage());
            assertTrue(l.getPercentage() >= 0);
        }
    }

    @Test
    void printAllCountriesTestNull() {
        db.printAllCountries(null);
    }

    @Test
    void printAllCountriesTestEmpty() {
        db.printAllCountries(new ArrayList<Country>());
    }

    @Test
    void printAllCountriesTestContainsNull() {
        ArrayList<Country> countries = new ArrayList<>();
        countries.add(null);
        db.printAllCountries(countries);
    }

    @Test
    void printAllCountries() {
        Country c = new Country();
        c.code = "ATA";
        c.name = "Antarctica";
        c.continent = "Antarctica";
        c.region = "Antarctica";
        c.population = 0;
        c.capitalID = 0;
        c.language = "";

        ArrayList<Country> countries = new ArrayList<>();
        countries.add(c);

        db.printAllCountries(countries);
    }
}