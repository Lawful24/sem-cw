package com.napier.semCw;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
}