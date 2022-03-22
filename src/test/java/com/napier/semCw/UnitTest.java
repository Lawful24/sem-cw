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
    void printAllCountriesInContinentTestNullList() {
        db.printAllCountriesInContinent(null, "Europe");
    }

    @Test
    void printAllCountriesInContinentTestNullString() {
        ArrayList<Country> countries = new ArrayList<>();
        countries.add(new Country("ATA", "Antarctica", "Antarctica", "Antarctica", 0, 0, ""));
        db.printAllCountriesInContinent(countries, null);
    }

    @Test
    void printAllCountriesInContinentTestEmptyList() {
        db.printAllCountriesInContinent(new ArrayList<>(), "Europe");
    }

    @Test
    void printAllCountriesInContinentTestEmptyString() {
        ArrayList<Country> countries = new ArrayList<>();
        countries.add(new Country("ATA", "Antarctica", "Antarctica", "Antarctica", 0, 0, ""));
        db.printAllCountriesInContinent(countries, "");
    }

    @Test
    void printAllCountriesInContinentTestContainsNull() {
        ArrayList<Country> countries = new ArrayList<>();
        countries.add(null);
        db.printAllCountriesInContinent(countries, "Europe");
    }

    @Test
    void printAllCountriesInContinentTestCountryNotFound() {
        ArrayList<Country> countries = new ArrayList<>();
        countries.add(new Country("ATA", "Antarctica", "Antarctica", "Antarctica", 0, 0, ""));
        db.printAllCountriesInContinent(countries, "Europe");
    }

    @Test
    void printAllCountriesInRegionTestNullList() {
        db.printAllCountriesInRegion(null, "Europe");
    }

    @Test
    void printAllCountriesInRegionTestNullString() {
        ArrayList<Country> countries = new ArrayList<>();
        countries.add(new Country("ATA", "Antarctica", "Antarctica", "Antarctica", 0, 0, ""));
        db.printAllCountriesInRegion(countries, null);
    }

    @Test
    void printAllCountriesInRegionTestEmptyList() {
        db.printAllCountriesInRegion(new ArrayList<>(), "Eastern Europe");
    }

    @Test
    void printAllCountriesInRegionTestEmptyString() {
        ArrayList<Country> countries = new ArrayList<>();
        countries.add(new Country("ATA", "Antarctica", "Antarctica", "Antarctica", 0, 0, ""));
        db.printAllCountriesInRegion(countries, "");
    }

    @Test
    void printAllCountriesInRegionTestContainsNull() {
        ArrayList<Country> countries = new ArrayList<>();
        countries.add(null);
        db.printAllCountriesInRegion(countries, "Eastern Europe");
    }

    @Test
    void printAllCountriesInRegionTestCountryNotFound() {
        ArrayList<Country> countries = new ArrayList<>();
        countries.add(new Country("ATA", "Antarctica", "Antarctica", "Antarctica", 0, 0, ""));
        db.printAllCountriesInRegion(countries, "Eastern Europe");
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
    void printTopNPopulatedCountriesTestNull() {
        db.printTopNPopulatedCountries(null, 10);
    }

    @Test
    void printTopNPopulatedCountriesTestEmpty() {
        db.printTopNPopulatedCountries(new ArrayList<>(), 10);
    }

    @Test
    void printTopNPopulatedCountriesTestContainsNull() {
        ArrayList<Country> countries = new ArrayList<>();
        countries.add(null);
        db.printTopNPopulatedCountries(countries, 1);
    }

    @Test
    void printTopNPopulatedCountriesTestContainsEmpty() {
        ArrayList<Country> countries = new ArrayList<>();
        countries.add(new Country());
        db.printTopNPopulatedCountries(countries, 1);
    }

    @Test
    void printTopNPopulatedCountriesTestOutOfRange() {
        ArrayList<Country> countries = new ArrayList<>();
        countries.add(new Country("USA", "United States Of America", "North America", "North America", 13000000, 1, "English"));
        countries.add(new Country("CAN", "Canada", "North America", "North America", 50000000, 2, "English"));
        db.printTopNPopulatedCountries(countries, 0);
        db.printTopNPopulatedCountries(countries, countries.size() + 1);
    }

    @Test
    void printTopNPopulatedCitiesTestNull() {
        db.printTopNPopulatedCities(null, 10);
    }

    @Test
    void printTopNPopulatedCitiesTestEmpty() {
        db.printTopNPopulatedCities(new ArrayList<>(), 10);
    }

    @Test
    void printTopNPopulatedCitiesTestContainsNull() {
        ArrayList<City> cities = new ArrayList<>();
        cities.add(null);
        db.printTopNPopulatedCities(cities, 1);
    }

    @Test
    void printTopNPopulatedCitiesTestContainsEmpty() {
        ArrayList<City> cities = new ArrayList<>();
        cities.add(new City());
        db.printTopNPopulatedCities(cities, 1);
    }

    @Test
    void printTopNPopulatedCitiesTestOutOfRange() {
        ArrayList<City> cities = new ArrayList<>();
        cities.add(new City(1, "Dean Town", "USA", "North Dakota", 5));
        cities.add(new City(2, "Lonely Town", "USA", "North Dakota", 1));
        db.printTopNPopulatedCities(cities, 0);
        db.printTopNPopulatedCities(cities, cities.size() + 1);
    }
}