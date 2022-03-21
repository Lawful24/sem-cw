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

    @Test
    void sortCountriesByPopulationTestAlreadySorted() {
        Country c1 = new Country();
        Country c2 = new Country();
        c1.code = "ASD";
        c2.code = "BSD";
        c1.population = 1;
        c2.population = 2;
        ArrayList<Country> countries = new ArrayList<>();
        countries.add(c1);
        countries.add(c2);
        db.sortCountriesByPopulation(countries);
    }

    @Test
    void sortCountriesByPopulation() {
        ArrayList<Country> countries = db.getAllCountriesFromDatabase();
        db.sortCountriesByPopulation(countries);
    }

    @Test
    void sortCitiesByPopulationTestNull() {
        db.sortCitiesByPopulation(null);
    }

    @Test
    void sortCitiesByPopulationTestEmpty() {
        db.sortCitiesByPopulation(new ArrayList<>());
    }

    @Test
    void sortCitiesByPopulationTestContainsNull() {
        ArrayList<City> cities = new ArrayList<>();
        cities.add(null);
        db.sortCitiesByPopulation(cities);
    }

    @Test
    void sortCitiesByPopulationTestContainsEmpty() {
        ArrayList<City> cities = new ArrayList<>();
        cities.add(new City());
        db.sortCitiesByPopulation(cities);
    }

    @Test
    void sortCitiesByPopulationTestAlreadySorted() {
        City c1 = new City();
        City c2 = new City();
        c1.setId(1);
        c2.setId(2);
        c1.setPopulation(1);
        c2.setPopulation(2);
        ArrayList<City> cities = new ArrayList<>();
        cities.add(c1);
        cities.add(c2);
        db.sortCitiesByPopulation(cities);
    }

    @Test
    void sortCitiesByPopulation() {
        ArrayList<City> cities = db.getAllCitiesFromDatabase();
        db.sortCitiesByPopulation(cities);
    }
}