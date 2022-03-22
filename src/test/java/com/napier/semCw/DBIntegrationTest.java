package com.napier.semCw;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DBIntegrationTest {
    static DatabaseSingleton db;

    @BeforeAll
    static void init()
    {
        db = DatabaseSingleton.getInstance();
        db.connect("localhost:33060", 30000);
    }

    /**
     * @Test  getCitiesInCountryOrganisedByLargest Method
     */
    @Test
    void TestgetCitiesInCountryOrganisedByLargest(){
        ArrayList<City> cities=db.getCitiesInCountryOrganisedByLargest("Romania");
        for( City c : cities ) {
            assertNotNull(c.getId());
            assertNotNull(c.getCountryCode());
            assertNotNull(c.getName());
            assertNotNull(c.getDistrictName());
            assertNotNull(c.getPopulation());
        }
    };

    /**
     * @Test  getCitiesInRegionOrganisedByLargest Method
     */
    @Test
    void TestgetCitiesInRegionOrganisedByLargest(){
        ArrayList<City> cities=db.getCitiesInRegionOrganisedByLargest("Eastern Europe");
        for( City c : cities ) {
            assertTrue(c.getId()>=0);
            assertNotNull(c.getCountryCode());
            assertNotNull(c.getName());
            assertNotNull(c.getDistrictName());
            assertTrue(c.getPopulation()>=0);
        }
    };
    /**
     * @Test  getCitiesInContinentOrganisedByLargest Method
     */
    @Test
    void TestgetCitiesInContinentOrganisedByLargest(){
        ArrayList<City> cities=db.getCitiesInContinentOrganisedByLargest("Europe");
        for( City c : cities ) {
            assertTrue(c.getId()>=0);
            assertNotNull(c.getCountryCode());
            assertNotNull(c.getName());
            assertNotNull(c.getDistrictName());
            assertTrue(c.getPopulation()>=0);
        }
    }
    /**
     * @Test  getCapitalCitiesInRegionOrganisedByLargest Method
     */
    @Test
    void TestgetCapitalCitiesInRegionOrganisedByLargest() {
        ArrayList<City> cities = db.getCapitalCitiesInRegionOrganisedByLargest("Eastern Europe");
        for (City c : cities) {
            assertTrue(c.getId() >= 0);
            assertNotNull(c.getCountryCode());
            assertNotNull(c.getName());
            assertNotNull(c.getDistrictName());
            assertTrue(c.getPopulation() >= 0);
        }
    }

    @Test
    void countryReportTest1() {
        String countryName = "Hungary";
        assertNotNull(db.printCountryReport(countryName));
    }

    @Test
    void countryReportTest3() {
        String countryName = "Kosovo";
        assertNull(db.printCountryReport(countryName));
    }

    @Test
    void cityReportTest1() {
        String cityName = "Budapest";
        assertNotNull(db.printCityReport(cityName));
    }

    @Test
    void cityReportTest2() {
        String cityName = "Hajduszoboszlo";
        assertNull(db.printCityReport(cityName));
    }

    @Test
    void capitalCityReportTest1() {
        String cityName = "Budapest";
        assertNotNull(db.printCapitalCityReport(cityName));
    }

    @Test
    void capitalCityReportTest2() {
        String cityName = "Hajduszoboszlo";
        assertNull(db.printCapitalCityReport(cityName));
    }

    @Test
    void worldPopulationTest(){
        assertTrue(db.printPopulationOfWorld() > 0);
    }

    @Test
    void regionPopulationTest1(){
        assertTrue(db.printPopulationOfRegion("Eastern Africa") > 0);
    }

    @Test
    void regionPopulationTest2(){
        assertTrue(db.printPopulationOfRegion("xd") == -1);
    }

    @Test
    void countryPopulationTest1(){
        assertTrue(db.printPopulationOfCountry("Hungary") > 0);
    }

    @Test
    void countryPopulationTest2(){
        assertTrue(db.printPopulationOfCountry("xd") == -1);
    }

    @Test
    void cityPopulationTest1(){
        assertTrue(db.printPopulationOfCity("Gyor") > 0);
    }

    @Test
    void cityPopulationTest2(){
        assertTrue(db.printPopulationOfCity("xd") == -1);
    }

    @Test
    void districtPopulationTest1(){
        assertTrue(db.printPopulationOfDistrict("Gyor-Moson-Sopron") > 0);
    }

    @Test
    void districtPopulationTest2(){
        assertTrue(db.printPopulationOfDistrict("xd") == -1);
    }

    @Test
    void continentPopulationTest1(){
        assertTrue(db.printPopulationOfContinent("Africa") > 0);
    }

    @Test
    void continentPopulationTest2(){
        assertTrue(db.printPopulationOfContinent("xd") == -1);
    }
    /**
     * @Test  topNCapitalCitiesInRegion Method
     */
    @Test
    void TesttopNCapitalCitiesInRegion() {
        ArrayList<City> cities = db.topNCapitalCitiesInRegion("Eastern Europe",3);
        for (City c : cities) {
            assertTrue(c.getId() >= 0);
            assertNotNull(c.getCountryCode());
            assertNotNull(c.getName());
            assertNotNull(c.getDistrictName());
            assertTrue(c.getPopulation() >= 0);
        }
    }
    /**
     * @Test topNCapitalCitiesInContinent Method
     */
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
    void printAllCountries() {
        db.printAllCountries(db.getAllCountriesFromDatabase());
    }

    @Test
    void printAllCountriesInContinent() {
        db.printAllCountriesInContinent(db.getAllCountriesFromDatabase(), "Antarctica");
    }

    @Test
    void printAllCountriesInRegion() {
        db.printAllCountriesInRegion(db.getAllCountriesFromDatabase(), "Antarctica");
    }

    @Test
    void printAllCities() {
        db.printAllCities(db.getAllCitiesFromDatabase());
    }

    @Test
    void printAllLanguages() {
        db.printAllLanguages(db.getAllLanguagesFromDatabase());
    }

    @Test
    void sortCountriesByPopulation() {
        ArrayList<Country> countries = db.getAllCountriesFromDatabase();
        db.sortCountriesByPopulation(countries);
    }

    @Test
    void sortCitiesByPopulation() {
        ArrayList<City> cities = db.getAllCitiesFromDatabase();
        db.sortCitiesByPopulation(cities);
    }

    @Test
    void printTopNPopulatedCountriesTest() {
        db.printTopNPopulatedCountries(db.getAllCountriesFromDatabase(), 10);
    }

    @Test
    void printTopNPopulatedCitiesTest() {
        db.printTopNPopulatedCities(db.getAllCitiesFromDatabase(), 10);
    }
    void TesttopNCapitalCitiesInContinent(){
        ArrayList<City> cities = db.topNCapitalCitiesInContinent("Europe",3);
        for (City c : cities) {
            assertTrue(c.getId() >= 0);
            assertNotNull(c.getCountryCode());
            assertNotNull(c.getName());
            assertNotNull(c.getDistrictName());
            assertTrue(c.getPopulation() >= 0);
        }
    };
    /**
     * @Test  topNCapitalCitiesInWorld Method
     */
    @Test
    void TesttopNCapitalCitiesInWorld(){
        ArrayList<City> cities = db.topNCapitalCitiesInWorld(3);
        for (City c : cities) {
            assertTrue(c.getId() >= 0);
            assertNotNull(c.getCountryCode());
            assertNotNull(c.getName());
            assertNotNull(c.getDistrictName());
            assertTrue(c.getPopulation() >= 0);
        }
    };
    /**
     * @Test  peopleICNCinEachCountry Method
     */
    @Test
    void TestpeopleICNCinEachCountry(){
        db.peopleICNCinEachCountry();
    };

}