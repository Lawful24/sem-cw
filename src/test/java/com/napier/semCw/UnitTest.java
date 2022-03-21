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
        db.printAllCountries(new ArrayList<>());
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

    @Test
    void printAllCitiesTestNull() {
        db.printAllCities(null);
    }

    @Test
    void printAllCitiesTestEmpty() {
        db.printAllCities(new ArrayList<>());
    }

    @Test
    void printAllCitiesTestContainsNull() {
        ArrayList<City> cities = new ArrayList<>();
        cities.add(null);
        db.printAllCities(cities);
    }

    @Test
    void printAllCities() {
        City c = new City();
        c.setId(84);
        c.setName("Santa Fé");
        c.setCountryCode("ARG");
        c.setDistrictName("Santa Fé");
        c.setPopulation(353063);

        ArrayList<City> cities = new ArrayList<>();
        cities.add(c);

        db.printAllCities(cities);
    }

    @Test
    void printAllLanguagesTestNull() {
        db.printAllLanguages(null);
    }

    @Test
    void printAllLanguagesTestEmpty() {
        db.printAllLanguages(new ArrayList<>());
    }

    @Test
    void printAllLanguagesTestContainsNull() {
        ArrayList<Language> languages = new ArrayList<>();
        languages.add(null);
        db.printAllLanguages(languages);
    }

    @Test
    void printAllLanguages() {
        Language l = new Language(
                "AZE", "Azerbaijani", true, 89.0
        );

        ArrayList<Language> languages = new ArrayList<>();
        languages.add(l);

        db.printAllLanguages(languages);
    }

    @Test
    void sortCountriesByPopulationTestNull() {
        db.sortCountriesByPopulation(null);
    }

    @Test
    void sortCountriesByPopulationTestEmpty() {
        db.sortCountriesByPopulation(new ArrayList<>());
    }

    @Test
    void sortCountriesByPopulationTestContainsNull() {
        ArrayList<Country> countries = new ArrayList<>();
        countries.add(null);
        db.sortCountriesByPopulation(countries);
    }

    @Test
    void sortCountriesByPopulationTestContainsEmpty() {
        ArrayList<Country> countries = new ArrayList<>();
        countries.add(new Country());
        db.sortCountriesByPopulation(countries);
    }
}